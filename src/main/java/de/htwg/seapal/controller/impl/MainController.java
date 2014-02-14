package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IMark;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.utils.logging.ILogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public final class MainController
        implements IMainController {

    private static final String KEY_BOAT = "boat";
    private static final String KEY_MARK = "mark";
    private static final String KEY_PERSON = "person";
    private static final String KEY_ROUTE = "route";
    private static final String KEY_TRIP = "trip";
    private static final String KEY_WAYPOINT = "waypoint";
    private static final String KEY_ACCOUNT = "account";

    private final Map<String, IDatabase<? extends IModel>> couchDBRepositorySupportDB = new HashMap<>();

    @Inject
    private ILogger logger;

    @Inject
    private IAccountController controller;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, IAccountDatabase accountDB) {

        couchDBRepositorySupportDB.put(KEY_BOAT, boatDB);
        couchDBRepositorySupportDB.put(KEY_MARK, markDB);
        couchDBRepositorySupportDB.put(KEY_PERSON, personDB);
        couchDBRepositorySupportDB.put(KEY_ROUTE, routeDB);
        couchDBRepositorySupportDB.put(KEY_TRIP, tripDB);
        couchDBRepositorySupportDB.put(KEY_WAYPOINT, waypointDB);
        couchDBRepositorySupportDB.put(KEY_ACCOUNT, accountDB);
    }

    @Override
    public Collection<? extends IModel> getSingleDocument(final String document, final String session, final UUID id) {
        return couchDBRepositorySupportDB.get(document).queryViews("singleDocument", session + id.toString());
    }

    @Override
    public boolean deleteDocument(final String document, final String session, final UUID id) {
        IDatabase<? extends IModel> database = couchDBRepositorySupportDB.get(document);
        ModelDocument doc = (ModelDocument) database.get(id);
        if (doc != null && doc.getAccount().equals(session)) {
            database.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public Collection<? extends IModel> getOwnDocuments(final String document, final String session) {
        return couchDBRepositorySupportDB.get(document).queryViews("own", session);
    }

    @Override
    public Collection<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return couchDBRepositorySupportDB.get(document).queryViews(parent, session + id.toString());
    }

    @Override
    public ModelDocument creatDocument(final String type, final ModelDocument document, String session) {
        if (document.getAccount() == null || document.getAccount().equals("")) {
            document.setAccount(session);
        }

        PublicPerson publicPerson = controller.getInternalInfo(session);
        boolean friendDocument = publicPerson.getFriendList().contains(document.getAccount());
        boolean askingPersonsDocument = publicPerson.getReceivedRequests().contains(document.getAccount());
        boolean ownDocument = document.getAccount().equals(session);
        if (!ownDocument && !askingPersonsDocument && !friendDocument) {
            return null;
        }

        if (document.isNew()) {
            couchDBRepositorySupportDB.get(type).create(document);
        } else {
            couchDBRepositorySupportDB.get(type).update(document);
        }
        return document;
    }

    @Override
    public Collection<? extends IModel> account(final UUID account, final String session) {
        IAccount person = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(account);
        if (person.getFriendList().contains(session) || person.getSentRequests().contains(session) || person.getId().equals(session)) {
            return couchDBRepositorySupportDB.get("person").queryViews("own", session);
        }

        return new LinkedList<>();
    }

    @Override
    public boolean addFriend(final String session, final UUID askedPersonUUID) {
        IAccount askingPerson = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(askedPersonUUID);

        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askedPerson);

        return returnVal;
    }

    @Override
    public Collection<? extends IModel> account(final String session) {
        return account(UUID.fromString(session), session);
    }

    @Override
    public Collection<? extends IModel> getDocuments(String document, String session, String scope) {
        Collection<IModel> Collection = new LinkedList<>();
        if (scope.equals("all") || scope.equals("own")) {
            Collection.addAll(getOwnDocuments(document, session));
        }

        if (scope.equals("all") || scope.equals("foreign") || scope.equals("friends")) {
            Collection.addAll(getFriendDocuments(document, session));
        }

        if (scope.equals("all") || scope.equals("foreign") || scope.equals("asking")) {
            Collection.addAll(getAskingDocuments(document, session));
        }

        return Collection;
    }

    private Collection<? extends IModel> getAskingDocuments(String document, String session) {
        IAccount person = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(UUID.fromString(session));

        Collection<IModel> Collection = new ArrayList<>();
        if (person != null) {
            for (String uuid : person.getReceivedRequests()) {
                Collection.addAll(getOwnDocuments(document, uuid));
            }
        }

        return Collection;
    }

    private Collection<? extends IModel> getFriendDocuments(String document, String session) {
        IAccount person = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(UUID.fromString(session));

        Collection<IModel> Collection = new ArrayList<>();
        if (person != null) {
            for (String uuid : person.getFriendList()) {
                Collection.addAll(getOwnDocuments(document, uuid));
            }
        }

        return Collection;
    }

    @Override
    public boolean addFriend(String session, String mail) {
        IAccount askingPerson = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(UUID.fromString(session));
        List<? extends IModel> askedPersons = couchDBRepositorySupportDB.get(KEY_ACCOUNT).queryViews("by_email", mail);
        IAccount askedPerson;
        if (askedPersons.size() == 0 || askedPersons.size() > 1) {
            return false;
        } else {
            askedPerson = (IAccount) askedPersons.get(0);
        }
        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askedPerson);

        return returnVal;
    }

    @Override
    public void abortRequest(String session, UUID id) {
        IAccount askingPerson = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) couchDBRepositorySupportDB.get(KEY_ACCOUNT).get(id);

        askingPerson.aboutRequest(askedPerson);

        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) couchDBRepositorySupportDB.get(KEY_ACCOUNT)).save(askedPerson);
    }

    @Override
    public boolean addPhoto(String session, UUID uuid, String contentType, File file) throws FileNotFoundException {
        Collection collection = getSingleDocument("mark", session, uuid);
        if (collection.size() == 1) {
            IMark mark = (IMark) collection.toArray()[0];
            if (mark.getAccount().equals(session)) {
                return ((IMarkDatabase) couchDBRepositorySupportDB.get(KEY_MARK)).addPhoto(mark, contentType, file);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public InputStream getPhoto(String session, UUID uuid) throws FileNotFoundException {
        Collection collection = getSingleDocument("mark", session, uuid);
        if (collection.size() == 1) {
            IMark mark = (IMark) collection.toArray()[0];
            if (mark.getAccount().equals(session)) {
                return ((IMarkDatabase) couchDBRepositorySupportDB.get(KEY_MARK)).getPhoto(mark.getUUID());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

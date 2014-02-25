package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.model.*;
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

    private static final String VIEW_SINGLEDOCUMENT = "singleDocument";
    private static final String VIEW_OWN = "own";
    private static final String VIEW_BY_EMAIL = "by_email";

    private final Map<String, IDatabase<? extends IModel>> DBConnections = new HashMap<>();

    @Inject
    private ILogger logger;

    @Inject
    private IAccountController controller;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, IAccountDatabase accountDB) {
        DBConnections.put(KEY_BOAT, boatDB);
        DBConnections.put(KEY_MARK, markDB);
        DBConnections.put(KEY_PERSON, personDB);
        DBConnections.put(KEY_ROUTE, routeDB);
        DBConnections.put(KEY_TRIP, tripDB);
        DBConnections.put(KEY_WAYPOINT, waypointDB);
        DBConnections.put(KEY_ACCOUNT, accountDB);
    }

    @Override
    public Collection<? extends IModel> getSingleDocument(final String document, final String session, final UUID id) {
        return DBConnections.get(document).queryViews(VIEW_SINGLEDOCUMENT, session + id.toString());
    }

    @Override
    public boolean deleteDocument(final String document, final String session, final UUID id) {
        IDatabase<? extends IModel> database = DBConnections.get(document);
        ModelDocument doc = (ModelDocument) database.get(id);
        if (doc == null) {
            return false;
        }

        PublicPerson publicPerson = controller.getInternalInfo(session, session);
        List<String> friendList = publicPerson.getFriend_list();
        boolean friendDocument = friendList.contains(doc.getAccount());
        boolean ownDocument = doc.getAccount().equals(session);

        if (ownDocument || friendDocument) {
            if (document.equals(KEY_TRIP)) {
                Collection<? extends IModel> docs = getByParent(KEY_WAYPOINT, KEY_TRIP, session, id);
                for (IModel waypoint : docs) {
                    deleteDocument(KEY_WAYPOINT, session, waypoint.getUUID());
                }
            }
            database.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public Collection<? extends IModel> getOwnDocuments(final String document, final String session) {
        return DBConnections.get(document).queryViews(VIEW_OWN, session);
    }

    @Override
    public Collection<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return DBConnections.get(document).queryViews(parent, session + id.toString());
    }

    @Override
    public ModelDocument creatDocument(final String type, final ModelDocument document, String session) {
        if (document.getAccount() == null || document.getAccount().equals("")) {
            document.setAccount(session);
        }

        PublicPerson publicPerson = controller.getInternalInfo(session, session);
        boolean friendDocument = publicPerson.getFriend_list().contains(document.getAccount());
        boolean ownDocument = document.getAccount().equals(session);
        if (!ownDocument && !friendDocument) {
            return null;
        }

        if (document.isNew()) {
            DBConnections.get(type).create(document);
        } else {
            DBConnections.get(type).update(document);
        }
        return document;
    }

    @Override
    public Collection<? extends IModel> account(final UUID account, final String session) {
        IAccount person = (IAccount) DBConnections.get(KEY_ACCOUNT).get(account);
        if (person.getFriendList().contains(session) || person.getSentRequests().contains(session) || person.getId().equals(session)) {
            return DBConnections.get(KEY_PERSON).queryViews(VIEW_OWN, session);
        }

        return new LinkedList<>();
    }

    @Override
    public boolean addFriend(final String session, final UUID askedPersonUUID) {
        IAccount askingPerson = (IAccount) DBConnections.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) DBConnections.get(KEY_ACCOUNT).get(askedPersonUUID);

        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askedPerson);

        return returnVal;
    }

    @Override
    public Collection<? extends IModel> account(final String session) {
        return account(UUID.fromString(session), session);
    }

    @Override
    public Collection<? extends IModel> getDocuments(String document, String session, String userid, String scope) {
        if (!session.equals(userid) && controller.getInternalInfo(session, userid) == null) {
            return new ArrayList<>();
        }

        Collection<IModel> Collection = new LinkedList<>();
        if (scope.equals("all") || scope.equals("own")) {
            Collection.addAll(getOwnDocuments(document, userid));
        }

        if (scope.equals("all") || scope.equals("friends")) {
            Collection.addAll(getFriendDocuments(document, userid));
        }

        return Collection;
    }

    @Override
    public Collection<? extends IModel> getAskingPerson(String session) {
        Collection<IModel> Collection = new LinkedList<>();
        Collection.addAll(getAskingDocuments(session));

        return Collection;
    }

    private Collection<? extends IModel> getAskingDocuments(String session) {
        IAccount person = (IAccount) DBConnections.get(KEY_ACCOUNT).get(UUID.fromString(session));

        Collection<IModel> Collection = new ArrayList<>();
        if (person != null) {
            for (String uuid : person.getReceivedRequests()) {
                Collection.addAll(getOwnDocuments(KEY_PERSON, uuid));
            }
        }

        return Collection;
    }

    private Collection<? extends IModel> getFriendDocuments(String document, String session) {
        IAccount person = (IAccount) DBConnections.get(KEY_ACCOUNT).get(UUID.fromString(session));

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
        IAccount askingPerson = (IAccount) DBConnections.get(KEY_ACCOUNT).get(UUID.fromString(session));
        List<? extends IModel> askedPersons = DBConnections.get(KEY_ACCOUNT).queryViews(VIEW_BY_EMAIL, mail);
        IAccount askedPerson;
        if (askedPersons.size() != 1) {
            // should never happen, because an email should be an unique identifier
            return false;
        } else {
            askedPerson = (IAccount) askedPersons.get(0);
        }
        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askedPerson);

        return returnVal;
    }

    @Override
    public void abortRequest(String session, UUID id) {
        IAccount askingPerson = (IAccount) DBConnections.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) DBConnections.get(KEY_ACCOUNT).get(id);

        askingPerson.aboutRequest(askedPerson);

        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askedPerson);
    }

    @Override
    public boolean addPhoto(String session, UUID uuid, String contentType, File file, String type) throws FileNotFoundException {
        Collection collection = getSingleDocument(type, session, uuid);
        if (collection.size() == 1) {
            if (type.equals(KEY_MARK)) {
                IMark mark = (IMark) collection.toArray()[0];
                return ((IMarkDatabase) DBConnections.get(KEY_MARK)).addPhoto(mark, contentType, file);
            } else if (type.equals(KEY_WAYPOINT)) {
                IWaypoint waypoint = (IWaypoint) collection.toArray()[0];
                return ((IWaypointDatabase) DBConnections.get(KEY_WAYPOINT)).addPhoto(waypoint, contentType, file);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public InputStream getPhoto(String session, UUID uuid, String type) throws FileNotFoundException {
        Collection collection = getSingleDocument(type, session, uuid);
        if (collection.size() == 1) {
            if (type.equals(KEY_MARK)) {
                IMark mark = (IMark) collection.toArray()[0];
                return ((IMarkDatabase) DBConnections.get(KEY_MARK)).getPhoto(mark.getUUID());
            } else if (type.equals(KEY_WAYPOINT)) {
                IWaypoint mark = (IWaypoint) collection.toArray()[0];
                return ((IWaypointDatabase) DBConnections.get(KEY_WAYPOINT)).getPhoto(mark.getUUID());
            }
        }

        return null;
    }
}

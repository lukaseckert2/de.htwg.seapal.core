package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.utils.logging.ILogger;
import org.ektorp.CouchDbInstance;
import org.ektorp.impl.StdCouchDbConnector;

import java.util.*;

public final class MainController
        implements IMainController {

    public static final String KEY_BOAT = "boat";
    public static final String KEY_MARK = "mark";
    public static final String KEY_PERSON = "person";
    public static final String KEY_ROUTE = "route";
    public static final String KEY_TRIP = "trip";
    public static final String KEY_WAYPOINT = "waypoint";
    private static final String KEY_ACCOUNT = "account";

    private Map<String, IDatabase<? extends IModel>> db = new HashMap<>();
    private Map<String, StdCouchDbConnector> stdDB = new HashMap<>();

    @Inject
    private ILogger logger;

    @Inject
    private IAccountController controller;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, IAccountDatabase accountDB, CouchDbInstance dbInstance) {

        db.put(KEY_BOAT, boatDB);
        stdDB.put(KEY_BOAT, new StdCouchDbConnector("seapal_boat_db", dbInstance));

        db.put(KEY_MARK, markDB);
        stdDB.put(KEY_MARK, new StdCouchDbConnector("seapal_mark_db", dbInstance));

        db.put(KEY_PERSON, personDB);
        stdDB.put(KEY_PERSON, new StdCouchDbConnector("seapal_person_db", dbInstance));

        db.put(KEY_ROUTE, routeDB);
        stdDB.put(KEY_ROUTE, new StdCouchDbConnector("seapal_route_db", dbInstance));

        db.put(KEY_TRIP, tripDB);
        stdDB.put(KEY_TRIP, new StdCouchDbConnector("seapal_trip_db", dbInstance));

        db.put(KEY_WAYPOINT, waypointDB);
        stdDB.put(KEY_WAYPOINT, new StdCouchDbConnector("seapal_waypoint_db", dbInstance));

        db.put(KEY_ACCOUNT, accountDB);
        stdDB.put(KEY_ACCOUNT, new StdCouchDbConnector("seapal_account_db", dbInstance));
    }

    @Override
    public Collection<? extends IModel> getSingleDocument(final String session, final UUID id, final String document) {
        return db.get(document).queryViews("singleDocument", session + id.toString());
    }

    @Override
    public boolean deleteDocument(final String session, final UUID id, final String document) {
        IDatabase<? extends IModel> key = db.get(document);
        ModelDocument doc = (ModelDocument) key.get(id);
        if (doc != null && doc.getAccount().equals(session)) {
            key.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public Collection<? extends IModel> getOwnDocuments(final String document, final String session) {
        return db.get(document).queryViews("own", session);
    }

    @Override
    public Collection<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return db.get(document).queryViews(parent, session + id.toString());
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
            stdDB.get(type).create(document);
        } else {
            stdDB.get(type).update(document);
        }
        return document;
    }

    @Override
    public Collection<? extends IModel> account(final UUID account, final String session) {
        IAccount person = (IAccount) db.get(KEY_ACCOUNT).get(account);
        if (person.getFriendList().contains(session) || person.getSentRequests().contains(session) || person.getId().equals(session)) {
            return db.get("person").queryViews("own", session);
        }

        return new LinkedList<>();
    }

    @Override
    public boolean addFriend(final String session, final UUID askedPersonUUID) {
        IAccount askingPerson = (IAccount) db.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) db.get(KEY_ACCOUNT).get(askedPersonUUID);

        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askedPerson);

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
        IAccount person = (IAccount) db.get(KEY_ACCOUNT).get(UUID.fromString(session));

        Collection<IModel> Collection = new ArrayList<>();
        if (person != null) {
            for (String uuid : person.getReceivedRequests()) {
                Collection.addAll(getOwnDocuments(document, uuid));
            }
        }

        return Collection;
    }

    private Collection<? extends IModel> getFriendDocuments(String document, String session) {
        IAccount person = (IAccount) db.get(KEY_ACCOUNT).get(UUID.fromString(session));

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
        IAccount askingPerson = (IAccount) db.get(KEY_ACCOUNT).get(UUID.fromString(session));
        List<? extends IModel> askedPersons = db.get(KEY_ACCOUNT).queryViews("by_email", mail);
        IAccount askedPerson;
        if (askedPersons.size() == 0 || askedPersons.size() > 1) {
            return false;
        } else {
            askedPerson = (IAccount) askedPersons.get(0);
        }
        boolean returnVal = askingPerson.addFriend(askedPerson);

        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askedPerson);

        return returnVal;
    }

    @Override
    public void abortRequest(String session, UUID id) {
        IAccount askingPerson = (IAccount) db.get(KEY_ACCOUNT).get(UUID.fromString(session));
        IAccount askedPerson = (IAccount) db.get(KEY_ACCOUNT).get(id);

        askingPerson.aboutRequest(askedPerson);

        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) db.get(KEY_ACCOUNT)).save(askedPerson);
    }
}

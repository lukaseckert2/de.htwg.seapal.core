package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.*;
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

    private Map<String, IDatabase<? extends IModel>> db = new HashMap<>();
    private Map<String, StdCouchDbConnector> stdDB = new HashMap<>();
    private Map<String, Class<? extends ModelDocument>> classes = new HashMap<>();

    private ILogger logger;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, ILogger logger, CouchDbInstance dbInstance) {
        this.logger = logger;
        this.logger.info("constructor", "Called MainController()");

        db.put(KEY_BOAT, boatDB);
        stdDB.put(KEY_BOAT, new StdCouchDbConnector("seapal_boat_db", dbInstance));
        classes.put(KEY_BOAT, Boat.class);

        db.put(KEY_MARK, markDB);
        stdDB.put(KEY_MARK, new StdCouchDbConnector("seapal_mark_db", dbInstance));
        classes.put(KEY_MARK, Mark.class);

        db.put(KEY_PERSON, personDB);
        stdDB.put(KEY_PERSON, new StdCouchDbConnector("seapal_person_db", dbInstance));
        classes.put(KEY_PERSON, Person.class);

        db.put(KEY_ROUTE, routeDB);
        stdDB.put(KEY_ROUTE, new StdCouchDbConnector("seapal_route_db", dbInstance));
        classes.put(KEY_ROUTE, Route.class);

        db.put(KEY_TRIP, tripDB);
        stdDB.put(KEY_TRIP, new StdCouchDbConnector("seapal_trip_db", dbInstance));
        classes.put(KEY_TRIP, Trip.class);

        db.put(KEY_WAYPOINT, waypointDB);
        stdDB.put(KEY_WAYPOINT, new StdCouchDbConnector("seapal_waypoint_db", dbInstance));
        classes.put(KEY_WAYPOINT, Waypoint.class);
    }

    @Override
    public List<? extends IModel> getSingleDocument(final String session, final UUID id, final String document) {
        return db.get(document).queryViews("singleDocument", session + id.toString());
    }

    @Override
    public boolean deleteDocument(final String session, final UUID id, final String document) {
        IDatabase<ModelDocument> key = (IDatabase<ModelDocument>) db.get(document);
        ModelDocument doc = key.get(id);
        if (doc.getAccount().equals(session)) {
            key.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public List<? extends IModel> getOwnDocuments(final String document, final String session) {
        return db.get(document).queryViews("own", session);
    }

    @Override
    public List<? extends IModel> getForeignDocuments(final String document, final String session) {
        IPerson person = (IPerson) db.get(KEY_PERSON).get(UUID.fromString(session));

        if (person != null && false) {
            List<IModel> list = new ArrayList<>();

            for (String uuid : person.getFriendList()) {
                list.addAll(getOwnDocuments(document, uuid));
            }

            return list;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return db.get(document).queryViews(parent, session + id.toString());
    }

    @Override
    public ModelDocument creatDocument(final String type, final ModelDocument document) {
        if (document.isNew()) {
            stdDB.get(type).create(document);
        } else {
            stdDB.get(type).update(document);
        }
        return document;
    }

    @Override
    public IPerson account(final UUID account, final String session) {
        IPerson person = (IPerson) db.get(KEY_PERSON).get(account);
        if (person.getFriendList().contains(session) || person.getSentRequests().contains(session) || person.getId().equals(session)) {
            return person;
        }

        return null;
    }

    @Override
    public boolean addFriend(final String session, final UUID askedPersonUUID) {
        IPerson askingPerson = (IPerson) db.get(KEY_PERSON).get(UUID.fromString(session));
        IPerson askedPerson = (IPerson) db.get(KEY_PERSON).get(askedPersonUUID);

        ((IPersonDatabase) db.get(KEY_PERSON)).save(askingPerson);
        ((IPersonDatabase) db.get(KEY_PERSON)).save(askedPerson);

        return askingPerson.addFriend(askedPerson);
    }
    @Override
    public IPerson account(final String session) {
        return account(UUID.fromString(session), session);
    }
}

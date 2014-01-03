package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.*;
import de.htwg.seapal.utils.logging.ILogger;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.impl.StdCouchDbConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class MainController
        implements IMainController {

    private Map<String, IDatabase<? extends IModel>> db = new HashMap<>();
    private Map<String, StdCouchDbConnector> stdDB = new HashMap<>();
    private Map<String, Class<? extends ModelDocument>> classes = new HashMap<>();

    private ILogger logger;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, ILogger logger, CouchDbInstance dbInstance) {
        this.logger = logger;
        this.logger.info("constructor", "Called MainController()");

        db.put("boat", boatDB);
        stdDB.put("boat", new StdCouchDbConnector("seapal_boat_db", dbInstance));
        classes.put("boat", Boat.class);

        db.put("mark", markDB);
        stdDB.put("mark", new StdCouchDbConnector("seapal_mark_db", dbInstance));
        classes.put("mark", Mark.class);

        db.put("person", personDB);
        stdDB.put("person", new StdCouchDbConnector("seapal_person_db", dbInstance));
        classes.put("person", Person.class);

        db.put("route", routeDB);
        stdDB.put("route", new StdCouchDbConnector("seapal_route_db", dbInstance));
        classes.put("route", Route.class);

        db.put("trip", tripDB);
        stdDB.put("trip", new StdCouchDbConnector("seapal_trip_db", dbInstance));
        classes.put("trip", Trip.class);

        db.put("waypoint", waypointDB);
        stdDB.put("waypoint", new StdCouchDbConnector("seapal_waypoint_db", dbInstance));
        classes.put("waypoint", Waypoint.class);
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

    public List<UUID> getAccessibleAccounts(final String session) {
        ViewQuery viewQuery = new ViewQuery();
        viewQuery.key(session);
        viewQuery.viewName("friends");
        return stdDB.get("person").queryView(viewQuery, UUID.class);
    }

    @Override
    public List<? extends IModel> getOwnDocuments(final String document, final String session) {
        return db.get(document).queryViews("own", session);
    }
    @Override
    public List<? extends IModel> getForeignDocuments(final String document, final String session) {
        ViewQuery viewQuery = new ViewQuery();
        viewQuery.keys(getAccessibleAccounts(session));

        return stdDB.get(document).queryView(viewQuery, classes.get(document));
    }
    @Override
    public List<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return db.get(document).queryViews(parent, session+id.toString());
    }

    @Override
    public String creatDocument(final String type, final ModelDocument document) {
        if (document.isNew()) {
            stdDB.get(type).create(document);
        } else {
            stdDB.get(type).update(document);
        }
        return document.get_id();
    }
}

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
    private static final String KEY_SETTING = "setting";

    private static final String VIEW_SINGLEDOCUMENT = "singleDocument";
    private static final String VIEW_OWN = "own";
    private static final String VIEW_BY_EMAIL = "by_email";

    private final Map<String, IDatabase<? extends IModel>> DBConnections = new HashMap<>();

    @Inject
    private ILogger logger;

    @Inject
    private IAccountController controller;

    @Inject
    public MainController(IBoatDatabase boatDB, IMarkDatabase markDB, IPersonDatabase personDB, IRouteDatabase routeDB, ITripDatabase tripDB, IWaypointDatabase waypointDB, IAccountDatabase accountDB, ISettingDatabase settingDB) {
        DBConnections.put(KEY_BOAT, boatDB);
        DBConnections.put(KEY_MARK, markDB);
        DBConnections.put(KEY_PERSON, personDB);
        DBConnections.put(KEY_ROUTE, routeDB);
        DBConnections.put(KEY_TRIP, tripDB);
        DBConnections.put(KEY_WAYPOINT, waypointDB);
        DBConnections.put(KEY_ACCOUNT, accountDB);
        DBConnections.put(KEY_SETTING, settingDB);
    }

    @Override
    public IModel getSingleDocument(final String document, final String session, final UUID id) {
        IModel result = DBConnections.get(document).get(id);

        PublicPerson person = controller.getInternalInfo(session, session);

        if (person.getId().equals(result.getAccount()) || person.getFriend_list().contains(result.getAccount())) {
            return result;
        }

        return null;
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

    /**
     * get a document by the reference to its parent document. For example, waypoints have a <b>n:1</b> relation to
     * trips and to boats. Since it is transitive, trips have a <b>n:1</b> connection to a boat. This means, a boat can
     * have <b>n</b> trips and a trip can have <b>n</b> waypoints. To make it faster, we don't save the reference to the
     * waypoints as an array into the boat or trip object, but we save the reference to its boat and trip into the
     * waypoint. CouchDB does the rest with a views named by the parent document types. This means: the waypoint db
     * contains a view which is named "boat" and one named "trip". To get the waypoints related to a boat, we search in
     * <b>waypoint db</b> in the view <b>boat</b> for the key which is created out of the session of the logged in user
     * and the key of the parent document
     *
     * @param document the document type we're searching for
     * @param parent the parent document type
     * @param session the session of the user
     * @param id the id of the parent document
     * @return list with the documents found
     */
    @Override
    public Collection<? extends IModel> getByParent(final String document, final String parent, final String session, final UUID id) {
        return DBConnections.get(document).queryViews(parent, session + id.toString());
    }

    /**
     * function to create a new document in the DB. This can be done for the own account or for the account of a friend.
     *
     * @param type     is the type of the document
     * @param document is the document to be created. it has been parsed from JSON to POJO
     * @param session  session is the session cookie of the logged in user
     * @return the document, that has been saved. It contains the UUID and the account it is related to
     */
    @Override
    public ModelDocument creatDocument(final String type, final ModelDocument document, String session) {
        // the app should set to which account belongs to the new document. If this is not the case, the currently
        // logged in user will be the owner.
        if (document.getAccount() == null || document.getAccount().equals("")) {
            document.setAccount(session);
        }

        // let the document check itself, if it is valid
        if(!document.isValid()) {
            return null;
        }

        // check if the owner of the new document exists
        PublicPerson publicPerson = controller.getInternalInfo(session, session);
        if (publicPerson == null) {
            return null;
        }

        // check if the currently logged in users friendlist contains the owner of the new document. If this is the case
        // a user creates a document for a friend.
        boolean friendDocument = publicPerson.getFriend_list().contains(document.getAccount());

        // this will happen more often: the currently logged in user creates a document for himself
        boolean ownDocument = document.getAccount().equals(session);
        if (!ownDocument && !friendDocument) {
            return null;
        }

        // save and return
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

        askingPerson.abortRequest(askedPerson);

        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askingPerson);
        ((IAccountDatabase) DBConnections.get(KEY_ACCOUNT)).save(askedPerson);
    }

    @Override
    public boolean addPhoto(String session, UUID uuid, String contentType, File file, String type) throws FileNotFoundException {
        IModel document = getSingleDocument(type, session, uuid);
        if (document != null) {
            if (type.equals(KEY_MARK)) {
                IMark mark = (IMark) document;
                return ((IMarkDatabase) DBConnections.get(KEY_MARK)).addPhoto(mark, contentType, file);
            } else if (type.equals(KEY_WAYPOINT)) {
                IWaypoint waypoint = (IWaypoint) document;
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
        IModel document = getSingleDocument(type, session, uuid);
        if (document != null) {
            if (type.equals(KEY_MARK)) {
                IMark mark = (IMark) document;
                return ((IMarkDatabase) DBConnections.get(KEY_MARK)).getPhoto(mark.getUUID());
            } else if (type.equals(KEY_WAYPOINT)) {
                IWaypoint waypoint = (IWaypoint) document;
                return ((IWaypointDatabase) DBConnections.get(KEY_WAYPOINT)).getPhoto(waypoint.getUUID());
            }
        }

        return null;
    }
}

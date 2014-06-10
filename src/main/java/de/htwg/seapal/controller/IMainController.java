package de.htwg.seapal.controller;

import de.htwg.seapal.database.IWaypointDatabase.WaypointPictureBean;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.IWaypoint;
import de.htwg.seapal.model.ModelDocument;

import org.ektorp.UpdateConflictException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IMainController {

    IModel getSingleDocument(String document, String session, UUID id);

    boolean deleteDocument(String document, String session, UUID id);

    Collection<? extends IModel> getOwnDocuments(String document, final String session);

    Collection<? extends IModel> getByParent(String document, String parent, String session, final UUID id);

    public ModelDocument creatDocument(final String type, final ModelDocument document, String session) throws UpdateConflictException;

    boolean addFriend(String session, UUID askedPersonUUID);

    Collection<? extends IModel> getDocuments(String document, String session, String userid, String scope);

    boolean addFriend(String session, String mail);

    void abortRequest(String session, UUID id);

    public String addPhoto(String session, UUID uuid, String contentType, File file, String type) throws FileNotFoundException;

    public InputStream getPhoto(String session, UUID uuid, String type) throws FileNotFoundException;

    Collection<? extends IModel> getAskingPerson(String session);
    
    List<WaypointPictureBean> getPhotosOfTrip(String session, UUID tripId, int startIndex, int count);
    
    List<? extends IWaypoint> getWaypointsByTripId(UUID tripId, int startIndex, int count);
    
    List<? extends ITrip> getTripsByBoatSlim(UUID boatId);
    
    List<? extends ITrip> getTripsByBoat(UUID boatId, long startDate, int skip, int count, boolean descending);
    
    List<? extends IWaypoint> getWaypointsOfTripSlim(UUID tripId);
}

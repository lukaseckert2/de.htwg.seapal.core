package de.htwg.seapal.database;

import de.htwg.seapal.model.IWaypoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * The waypoint database interface.
 * <p>
 * Note: all methods which use a generated view must:
 * 		 - generated view name: by_[property]
 *       - be named: findBy[Property]
 *       - only have 1 parameter
 *       - [Property] must exist in target class (by_color -> e.g. public String getColor())
 *       - iterable fields: plural -> getColors()
 *       - initStandardDesignDocument() of CouchDbRepositorySupport must be triggered
 * Remark: the design file "_design/Waypoint" must be created manually.
 * </p>
 */
public interface IWaypointDatabase extends IDatabase<IWaypoint> {
	
	public static class WaypointPictureBean {
		private String waypointId;
		private String thumbPicture;

		public String getWaypointId() {
			return waypointId;
		}
		public void setWaypointId(String waypointId) {
			this.waypointId = waypointId;
		}
		/**
		 * Returns the thumb image data in the form "data:image/jpg;base64,[binaryData]"
		 * to be set as img-Tag directly.
		 */
		public String getThumbPicture() {
			return thumbPicture;
		}
		public void setThumbPicture(String thumbPicture) {
			this.thumbPicture = thumbPicture;
		}
	}
	
    String addPhoto(IWaypoint mark, String contentType, File file) throws FileNotFoundException;

    InputStream getPhoto(UUID uuid);
    
    /**
	 * Gets all waypoints of a trip which have a picture assigned.
	 * Returns a list of JSON objects of the form {waypointId, thumbImage}.
	 * thumbImage is of the form "data:image/jpg;base64,[binaryData]" for direct use as src of image tags.
	 * @param startIndex Number of entries to skip before returning the values.
	 */
	List<WaypointPictureBean> getPhotosByTripId(UUID tripId, int startIndex, int count);
 
	
	/**
	 * Gets the waypoints objects of a trip.
	 * @param startIndex Number of entries to skip before returning the values.
	 * @param count Specify 0 to reveive all items.
	 */
	List<? extends IWaypoint> getWaypointsByTripId(UUID tripId, int startIndex, int count);
	
	/**
	 * Returns all waypoints of the specified trip. Note that not all properties get initialized!
	 */
	List<? extends IWaypoint> getWaypointsOfTripSlim(UUID tripId);
}

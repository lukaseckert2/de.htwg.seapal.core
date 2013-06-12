package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import org.ektorp.support.GenerateView;

import de.htwg.seapal.model.IWaypoint;

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
	/**
	 * Gets all waypoints by the given trip UUID.
	 * @param tripId The trips UUID.
	 * @return All matching waypoints.
	 */
	@GenerateView
	List<IWaypoint> findByTrip(UUID tripId);
}

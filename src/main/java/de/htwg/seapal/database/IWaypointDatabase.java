package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IWaypoint;


public interface IWaypointDatabase extends IDatabase {

	/**
	 * Saves a waypoint.
	 * @param waypoint The waypoint to save.
	 */
	void save(IWaypoint waypoint);

	/**
	 * Gets a waypoint with a given UUID.
	 * @param id The UUID of the waypoint.
	 * @return The waypoint with the given UUID or NULL,
	 *         if no waypoint was found.
	 */
	IWaypoint get(UUID id);

	/**
	 * Gets a list of all waypoints.
	 * @return All waypoints.
	 */
	List<IWaypoint> getAll();
}

package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.ITrip;


public interface ITripDatabase extends IDatabase {

	/**
	 * Saves a trip.
	 * @param trip The trip to save.
	 */
	void save(ITrip trip);

	/**
	 * Gets a trip with a given UUID.
	 * @param id The UUID of the trip.
	 * @return The trip with the given UUID or NULL,
	 *         if no trip was found.
	 */
	ITrip get(UUID id);

	/**
	 * Gets a list of all trips.
	 * @return All trips.
	 */
	List<ITrip> getAll();
}

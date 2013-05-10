package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.ITrip;


public interface ITripDatabase<T extends ITrip> extends IDatabase {

	/**
	 * Saves a trip.
	 * @param trip The trip to save.
	 */
	void save(T trip);

	/**
	 * Gets a trip with a given UUID.
	 * @param id The UUID of the trip.
	 * @return The trip with the given UUID or NULL,
	 *         if no trip was found.
	 */
	T get(UUID id);

	/**
	 * Gets a list of all trips.
	 * @return All trips.
	 */
	List<T> getAll();
}

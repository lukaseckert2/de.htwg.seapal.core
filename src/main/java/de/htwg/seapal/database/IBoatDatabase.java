package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IBoat;


public interface IBoatDatabase extends IDatabase {

	/**
	 * Saves a boat.
	 * @param boat The boat to save.
	 */
	void save(IBoat boat);

	/**
	 * Gets a boat with a given UUID.
	 * @param id The UUID of the boat.
	 * @return The boat with the given UUID or NULL,
	 *         if no boat was found.
	 */
	IBoat get(UUID id);

	/**
	 * Gets a list of all boats.
	 * @return All boats.
	 */
	List<IBoat> getAll();
}
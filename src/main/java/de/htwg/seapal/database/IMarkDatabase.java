package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IMark;

public interface IMarkDatabase extends IDatabase {

	/**
	 * Saves a mark.
	 * @param mark The mark to save.
	 */
	void save(IMark mark);

	/**
	 * Gets a mark with a given UUID.
	 * @param id The UUID of the mark.
	 * @return The mark with the given UUID or NULL,
	 *         if no mark was found.
	 */
	IMark get(UUID id);

	/**
	 * Gets a list of all marks.
	 * @return All marks.
	 */
	List<IMark> getAll();
}

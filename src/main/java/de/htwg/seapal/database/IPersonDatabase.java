package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IPerson;

public interface IPersonDatabase extends IDatabase {

	/**
	 * Saves a person.
	 * @param person The person to save.
	 */
	void save(IPerson person);

	/**
	 * Gets a person with a given UUID.
	 * @param id The UUID of the person.
	 * @return The person with the given UUID or NULL,
	 *         if no person was found.
	 */
	IPerson get(UUID id);

	/**
	 * Gets a list of all persons.
	 * @return All persons.
	 */
	List<IPerson> getAll();
}

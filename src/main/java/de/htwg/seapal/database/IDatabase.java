package de.htwg.seapal.database;

import java.util.UUID;

public interface IDatabase {
	/**
	 * Opens the database connection.
	 * @return TRUE, if successful.
	 */
	boolean open();
	
	/**
	 * Creates a new data.
	 * @return The UUID of the created data.
	 */
	UUID create();

	/**
	 * Deletes the data with the given UUID.
	 * @param id The UUID of the data to delete.
	 */
	void delete(UUID id);
	
	/**
	 * Closes the database connection.
	 * @return TRUE, if successful.
	 */
	boolean close();
}

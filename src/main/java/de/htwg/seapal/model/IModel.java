package de.htwg.seapal.model;

import java.util.UUID;

public interface IModel {
	/**
	 * Gets the UUID of the data.
	 *
	 * @return The UUID of the data.
	 */
	UUID getUUID();

	/**
	 * Gets the String converted UUID.
	 *
	 * @return The UUID of the data as String.
	 */
	String getId();

    void setId(String uuid);

    String getAccount();

    void setAccount(String uuid);

    String getRevision();

    boolean isValid();
}

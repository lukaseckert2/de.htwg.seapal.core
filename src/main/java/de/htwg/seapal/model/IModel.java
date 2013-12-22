package de.htwg.seapal.model;

import java.util.List;
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

    String getOwner();

    void setOwner(String owner);

    List<String> getCrew();

    void setCrew(List<String> crew);

    void addCrewMember(String crewMember);
}

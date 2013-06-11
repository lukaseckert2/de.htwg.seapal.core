package de.htwg.seapal.model;

import java.util.List;
import java.util.UUID;

public interface IRace extends IModel {
	String getName();
	void setName(String name);
	
	/**
	 * Gets the name of the boat class.
	 * @return The name of the boat class.
	 */
	String getBoatClass();
	
	/**
	 * Sets the name of the boat class.
	 * @param boatClass The name of the boat class.
	 */
	void setBoatClass(String boatClass);
	
	List<UUID> getTrips();
	void addTrip(UUID tripId);
	
	List<UUID> getMarkPassings();
	void addMarkPassing(UUID markId);
	
	List<UUID> getStart();
	void setStart(UUID markId1, UUID markId2);
	
	List<UUID> getGoal();
	void setGoal(UUID markId1, UUID markId2);
}

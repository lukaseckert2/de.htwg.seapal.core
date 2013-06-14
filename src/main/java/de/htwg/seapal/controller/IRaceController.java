package de.htwg.seapal.controller;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IRace;
import de.htwg.seapal.model.IRace.RaceControlPoint;
import de.htwg.seapal.model.IRace.RaceTrip;
import de.htwg.seapal.utils.observer.IObservable;

public interface IRaceController extends IObservable {
	/**
	 * Sets the race name.
	 * @param id The race UUID.
	 * @param name The new race name.
	 */
	void setName(UUID id, String name);
	
	/**
	 * Gets the race name.
	 * @param id The race UUID.
	 * @return The race name.
	 */
	String getName(UUID id);
	
	/**
	 * Sets the boat class.
	 * @param id The race UUID.
	 * @param name The new boat class.
	 */
	void setBoatClass(UUID id, String boatClass);
	
	/**
	 * Gets the race name.
	 * @param id The race UUID.
	 * @return The boat class.
	 */
	String getBoatClass(UUID id);
	
	/**
	 * Gets the trips.
	 * @param id The race UUID.
	 * @return The trips of the race.
	 */
	List<RaceTrip> getTrips(UUID id);
	
	/**
	 * Sets the trips.
	 * @param id The race UUID.
	 * @param trips The new trips of the race.
	 */
	void setTrips(UUID id, List<RaceTrip> trips);
	
	/**
	 * Gets the control points.
	 * @param id The race UUID.
	 * @return The control points of the race.
	 */
	List<RaceControlPoint> getControlPoints(UUID id);
	
	/**
	 * Sets the control points.
	 * @param id The race UUID.
	 * @param trips The new control points of the race.
	 */
	void setControlPoints(UUID id, List<RaceControlPoint> controlPoints);
	
	/**
	 * Creates a new race,
	 * @return The race ID.
	 */
	UUID newRace();
	
	/**
	 * Deletes the race.
	 * @param id The race UUID to delete.
	 */
	void deleteRace(UUID id);
	
	/**
	 * Closes the database connection.
	 */
	void closeDB();
	
	/**
	 * Gets a list of all race UUIDs.
	 * @return All race UUIDs.
	 */
	List<UUID> getRaces();
	
	/**
	 * Gets a race by the given race UUID.
	 * @param raceId The race UUID.
	 * @return The race.
	 */
	IRace getRace(UUID raceId);
	
	/**
	 * Gets all races.
	 * @return All races.
	 */
	List<IRace> getAllRaces();
	
	/**
	 * Saves the race.
	 * @param race The race to save.
	 * @return Returns TRUE, if the race was newly created and FALSE when the
	 *         race was updated.
	 */
	boolean saveRace(IRace race);
}

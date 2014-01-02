package de.htwg.seapal.controller;

import de.htwg.seapal.model._IRace;
import de.htwg.seapal.model._IRace.RaceControlPoint;
import de.htwg.seapal.model._IRace.RaceTrip;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.UUID;

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
	 * @param boatClass The new boat class.
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
	 * @param controlPoints The new control points of the race.
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
	_IRace getRace(UUID raceId);

	/**
	 * Gets all races.
	 * @return All races.
	 */
	List<_IRace> getAllRaces();

	/**
	 * Saves the race.
	 * @param race The race to save.
	 * @return Returns TRUE, if the race was newly created and FALSE when the
	 *         race was updated.
	 */
	boolean saveRace(_IRace race);

    List<? extends _IRace> queryView(String viewName, String key);
}

package de.htwg.seapal.controller;

import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.UUID;

public interface ITripController extends IObservable {

	String getName(UUID id);

	void setName(UUID id, String name);

	String getStartLocation(UUID id);

	void setStartLocation(UUID id, String location);

	String getEndLocation(UUID id);

	void setEndLocation(UUID id, String location);

	UUID getSkipper(UUID id);

	void setSkipper(UUID id, UUID skipper);

	String getCrewMembers(UUID id);

	void setCrewMember(UUID id, String crewMember);

	long getStartTime(UUID id);

	void setStartTime(UUID id, long start);

	long getEndTime(UUID id);

	void setEndTime(UUID id, long end);

	int getMotor(UUID id);

	void setMotor(UUID id, int motor);

	double getFuel(UUID id);

	void setFuel(UUID id, double percent);

	String getNotes(UUID id);

	void setNotes(UUID id, String text);

	String getString(UUID id);

	void deleteTrip(UUID id);

	void closeDB();

	String getBoat(UUID id);

	List<UUID> getTrips();

	UUID newTrip(UUID boat);

	List<UUID> getTrips(String boatId);

    List<? extends ITrip> queryView(String viewName, String key);

	/**
	 * Saves the trip.
	 *
	 * @param trip
	 *            The trip to save.
	 * @return Returns TRUE, if the trip was newly created and FALSE when the
	 *         trip was updated.
	 */
	boolean saveTrip(ITrip trip);
}

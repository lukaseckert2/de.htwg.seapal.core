package de.htwg.seapal.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.inject.Inject;

import de.htwg.seapal.controller.ITripController;
import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.utils.observer.Observable;
import de.htwg.seapal.utils.logging.ILogger;

public class TripController extends Observable implements ITripController {

	private ITripDatabase db;
	private final ILogger logger;

	@Inject
	public TripController(ITripDatabase db, ILogger logger) {
		this.db = db;
		this.logger = logger;
	}

	@Override
	public String getName(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return trip.getName();

	}

	@Override
	public void setName(UUID id, String name) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setName(name);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public void setStartLocation(UUID id, String location) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setStartLocation(location);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public String getStartLocation(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return trip.getStartLocation();

	}

	@Override
	public void setEndLocation(UUID id, String location) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setEndLocation(location);
		db.save(trip);
		notifyObservers();

	}

	@Override
	public String getEndLocation(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return trip.getEndLocation();

	}

	@Override
	public void setSkipper(UUID id, UUID skipper) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setSkipper(skipper.toString());
		db.save(trip);
		notifyObservers();

	}

	@Override
	public UUID getSkipper(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return UUID.fromString(trip.getSkipper());

	}

	@Override
	public void addCrewMember(UUID id, String crewMember) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.addCrewMember(crewMember);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public List<String> getCrewMembers(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return trip.getCrewMembers();
	}

	@Override
	public void setStartTime(UUID id, long start) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setStartTime(start);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public long getStartTime(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return -1;
		return trip.getStartTime();
	}

	@Override
	public void setEndTime(UUID id, long end) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setEndTime(end);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public long getEndTime(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return -1;
		return trip.getEndTime();
	}

	@Override
	public void setDuration(UUID id, long timeInSeconds) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setDuration(timeInSeconds);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public long getDuration(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return -1;
		return trip.getDuration();
	}

	@Override
	public void setMotor(UUID id, int motor) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setMotor(motor);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public int getMotor(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return -1;
		return trip.getMotor();
	}

	@Override
	public void setFuel(UUID id, double percent) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setFuel(percent);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public double getFuel(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return -1;
		return trip.getFuel();
	}

	@Override
	public void setNotes(UUID id, String text) {
		ITrip trip = db.get(id);
		if (trip == null)
			return;
		trip.setNotes(text);
		db.save(trip);
		notifyObservers();
	}

	@Override
	public String getNotes(UUID id) {
		ITrip trip = db.get(id);
		if (trip == null)
			return null;
		return trip.getNotes();
	}

	@Override
	public String getString(UUID id) {
		return " ID = \t" + id + "\n Name = \t" + getName(id)
				+ "\n startLocation = \t" + getStartLocation(id)
				+ "\n endLocation = \t" + getEndLocation(id)
				+ "\n skipper = \t" + getSkipper(id) + "\n crew = \t"
				+ getCrewMembers(id) + "\n startTime = \t" + getStartTime(id)
				+ "\n endTime = \t" + getEndTime(id) + "\n duration = \t"
				+ getDuration(id) + "\n motor = \t" + getMotor(id)
				+ "\n fuel = \t" + getFuel(id) + "\n notes = \t" + getNotes(id)
				+ "\n boat = \t" + getTrip(id) + "\n";
	}

	@Override
	public UUID newTrip(UUID boat) {
		UUID newTrip = db.create();
		ITrip trip = db.get(newTrip);
		trip.setBoat(boat.toString());
		db.save(trip);
		notifyObservers(); // ??
		return newTrip;
	}

	@Override
	public void deleteTrip(UUID id) {
		db.delete(id);
		notifyObservers();
	}

	@Override
	public final void closeDB() {
		db.close();
		logger.info("TripController", "Database closed");
	}

	@Override
	public List<UUID> getTrips() {
		List<ITrip> trips = db.loadAll();
		List<UUID> list = new ArrayList<UUID>();
		for (ITrip trip : trips) {
			list.add(UUID.fromString(trip.getId()));
		}
		return list;
	}

	@Override
	public List<UUID> getTrips(UUID boatId) {
		List<ITrip> query = db.loadAll();
		logger.info("TripController", query.toString());
		// TODO: filtering should be moved to database layer.
		List<UUID> list = new ArrayList<UUID>();
		for (ITrip trip : query) {
			if (trip.getBoat().equals(boatId.toString()))
				list.add(UUID.fromString(trip.getId()));
		}
		return list;
	}

	@Override
	public ITrip getTrip(UUID tripId) {
		return db.get(tripId);
	}

	@Override
	public List<ITrip> getAllTrips() {
		return db.loadAll();
	}

	@Override
	public List<ITrip> getAllTrips(UUID boatId) {
		List<ITrip> trips = db.loadAll();
		logger.info("TripController", trips.toString());
		// TODO: filtering should be moved to database layer.
		for (int i = trips.size() - 1; i >= 0; ++i) {
			if (!trips.get(i).getBoat().equals(boatId.toString())) {
				trips.remove(i);
			}
		}
		return trips;
	}

	@Override
	public boolean saveTrip(ITrip trip) {
		return db.save(trip);
	}
}
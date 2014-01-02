package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IRaceController;
import de.htwg.seapal.database.IRaceDatabase;
import de.htwg.seapal.model._IRace;
import de.htwg.seapal.model._IRace.RaceControlPoint;
import de.htwg.seapal.model._IRace.RaceTrip;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RaceController extends Observable implements IRaceController {

	protected IRaceDatabase db;
	private final ILogger logger;

	@Inject
	public RaceController(IRaceDatabase db, ILogger logger) {
		this.db = db;
		this.logger = logger;
	}

	@Override
	public void setName(UUID id, String name) {
		_IRace race = db.get(id);
		if (race == null)
			return;
		race.setName(name);
		db.save(race);
		notifyObservers();
	}

	@Override
	public String getName(UUID id) {
		_IRace race = db.get(id);
		if (race == null)
			return null;
		return db.get(id).getName();
	}

	@Override
	public void setBoatClass(UUID id, String boatClass) {
		_IRace race = db.get(id);
		if (race == null)
			return;
		race.setBoatClass(boatClass);
		db.save(race);
		notifyObservers();
	}

	@Override
	public String getBoatClass(UUID id) {
		_IRace race = db.get(id);
		if (race == null)
			return null;
		return db.get(id).getBoatClass();
	}

	@Override
	public List<RaceTrip> getTrips(UUID id) {
		_IRace race = db.get(id);
		if (race == null)
			return null;
		return db.get(id).getTrips();
	}

	@Override
	public void setTrips(UUID id, List<RaceTrip> trips) {
		_IRace race = db.get(id);
		if (race == null)
			return;
		race.setTrips(trips);
		db.save(race);
		notifyObservers();
	}

	@Override
	public List<RaceControlPoint> getControlPoints(UUID id) {
		_IRace race = db.get(id);
		if (race == null)
			return null;
		return db.get(id).getControlPoints();
	}

	@Override
	public void setControlPoints(UUID id, List<RaceControlPoint> controlPoints) {
		_IRace race = db.get(id);
		if (race == null)
			return;
		race.setControlPoints(controlPoints);
		db.save(race);
		notifyObservers();
	}

	@Override
	public UUID newRace() {
		return db.create();
	}

	@Override
	public void deleteRace(UUID id) {
		db.delete(id);
		notifyObservers();
	}

	@Override
	public void closeDB() {
		db.close();
		logger.info("RaceController", "Database closed");
	}

	@Override
	public List<UUID> getRaces() {
		List<_IRace> query = db.loadAll();
		List<UUID> list = new ArrayList<UUID>();
		for (_IRace race : query) {
			list.add(race.getUUID());
		}
		return list;
	}

	@Override
	public _IRace getRace(UUID raceId) {
		return db.get(raceId);
	}

	@Override
	public List<_IRace> getAllRaces() {
		return db.loadAll();
	}

	@Override
	public boolean saveRace(_IRace race) {
		return db.save(race);
	}
    @Override
    public List<? extends _IRace> queryView(final String viewName, final String key) {
        return db.queryViews(viewName, key);
    }
}

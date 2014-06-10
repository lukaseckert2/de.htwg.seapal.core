package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IRaceDatabase;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model._IRace;
import de.htwg.seapal.model._IRace.*;
import de.htwg.seapal.model.impl._Race;

import java.util.*;

public class RaceDatabase implements IRaceDatabase {

	private final Map<UUID, _IRace> db = new HashMap<>();
	private _IRace newRace;

	public RaceDatabase() {
		open();
	}

	@Override
	public boolean open() {
		// create test data
		saveNewRaceInDatabase("NEW-_Race", "Class");
		for (int i = 1; i < 10; i++) {
			saveNewRaceInDatabase("_Race-" + i, "Class");
		}
		return true;
	}

	@Override
	public UUID create() {
		return newRace.getUUID();
	}

	private UUID saveNewRaceInDatabase(String name, String boatClass) {
		_IRace race = new _Race();
		race.setName(name);
		race.setBoatClass(boatClass);
		race.setTrips(generateTrips());
		race.setControlPoints(generateControlPoints());

		save(race);
		return race.getUUID();
	}

	private List<RaceControlPoint> generateControlPoints() {
		List<RaceControlPoint> controlPoints = new ArrayList<>();
		controlPoints.add(generateLine(0.1, 0.1, 0.1, 0.2));
		controlPoints.add(generateMark(0.1, 0.1));
		controlPoints.add(generateMark(0.2, 0.2));
		controlPoints.add(generateLine(0.4, 0.4, 0.5, 0.5));
		return controlPoints;
	}

	private RaceControlPoint generateLine(double lat1, double lng1, double lat2, double lng2) {
		List<RaceCoordinate> coords = new ArrayList<>();
		coords.add(new RaceCoordinate(lat1, lng1));
		coords.add(new RaceCoordinate(lat2, lng2));
		return new RaceControlPoint(UUID.randomUUID().toString(), "line", coords);
	}

	private RaceControlPoint generateMark(double lat, double lng) {
		List<RaceCoordinate> coords = new ArrayList<>();
		coords.add(new RaceCoordinate(lat, lng));
		return new RaceControlPoint(UUID.randomUUID().toString(), "buoy", coords);
	}

	private List<RaceTrip> generateTrips() {
		List<RaceTrip> trips = new ArrayList<>();
		trips.add(new RaceTrip(
				UUID.randomUUID().toString(),
				"trip1",
				generateBoat("trip1-boatname"),
				generateWaypoints(0.0)));
		trips.add(new RaceTrip(UUID.randomUUID().toString(),
				"trip2",
				generateBoat("trip2-boatname"),
				generateWaypoints(0.1)));
		return trips;
	}

	private List<RaceWaypoint> generateWaypoints(Double posOffset) {
		List<RaceWaypoint> waypoints = new ArrayList<>();
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(),
				new RaceCoordinate(0.1 + posOffset, 0.3 + posOffset), 1000000000L, 2, 3, 4, 5, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(),
				new RaceCoordinate(0.2 + posOffset, 0.4 + posOffset), 1000001000L, 3, 3, 4, 5, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(),
				new RaceCoordinate(0.3 + posOffset, 0.5 + posOffset), 1000002000L, 4, 3, 4, 5, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(),
				new RaceCoordinate(0.4 + posOffset, 0.6 + posOffset), 1000003000L, 4, 4, 5, 6, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(),
				new RaceCoordinate(0.4 + posOffset, 0.7 + posOffset), 1000004000L, 3, 5, 5, 4, null));
		return waypoints;
	}

	private RaceBoat generateBoat(String boatName) {
		return new RaceBoat(UUID.randomUUID().toString(), boatName, "GER");
	}

	@Override
	public boolean save(_IRace data) {
		if (!db.containsKey(data.getUUID())) {
			newRace = data;
		}
		db.put(data.getUUID(), data);
		return true;
	}

	@Override
	public _IRace get(UUID id) {
		return new _Race(db.get(id));
	}

	@Override
	public List<_IRace> loadAll() {
		return ImmutableList.copyOf(db.values());
	}

	@Override
	public void delete(UUID id) {
		db.remove(id);
	}

	@Override
	public boolean close() {
		return true;
	}

    @Override
    public void create(ModelDocument document) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<? extends _IRace> queryViews(final String viewName, final String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ModelDocument document) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

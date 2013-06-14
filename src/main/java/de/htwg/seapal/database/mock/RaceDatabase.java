package de.htwg.seapal.database.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import de.htwg.seapal.database.IRaceDatabase;
import de.htwg.seapal.model.IRace;
import de.htwg.seapal.model.IRace.RaceBoat;
import de.htwg.seapal.model.IRace.RaceControlPoint;
import de.htwg.seapal.model.IRace.RaceCoordinate;
import de.htwg.seapal.model.IRace.RaceTrip;
import de.htwg.seapal.model.IRace.RaceWaypoint;
import de.htwg.seapal.model.impl.Race;

public class RaceDatabase implements IRaceDatabase {

	Map<UUID, IRace> db = new HashMap<UUID, IRace>();
	private IRace newRace;
	
	public RaceDatabase() {
		open();
	}
	
	@Override
	public boolean open() {
		// create test data
		saveNewRaceInDatabase("NEW-Race", "Class");
		for (int i = 1; i < 10; i++) {
			saveNewRaceInDatabase("Race-" + i, "Class");
		}
		return true;
	}

	@Override
	public UUID create() {
		return newRace.getUUID();
	}
	
	private UUID saveNewRaceInDatabase(String name, String boatClass) {
		IRace race = new Race();
		race.setName(name);
		race.setBoatClass(boatClass);
		race.setTrips(generateTrips());
		race.setControlPoints(generateControlPoints());
		
		save(race);
		return race.getUUID();
	}

	private List<RaceControlPoint> generateControlPoints() {
		List<RaceControlPoint> controlPoints = new ArrayList<RaceControlPoint>();
		controlPoints.add(generateLine(0.1, 0.1, 0.1, 0.2));
		controlPoints.add(generateMark(0.1, 0.1));
		controlPoints.add(generateMark(0.2, 0.2));
		controlPoints.add(generateLine(0.4, 0.4, 0.5, 0.5));
		return controlPoints;
	}
	
	private RaceControlPoint generateLine(double lat1, double lng1, double lat2, double lng2) {
		List<RaceCoordinate> coords = new ArrayList<RaceCoordinate>();
		coords.add(new RaceCoordinate(lat1, lng1));
		coords.add(new RaceCoordinate(lat2, lng2));
		return new RaceControlPoint(UUID.randomUUID().toString(), coords);
	}
		private RaceControlPoint generateMark(double lat, double lng) {
		List<RaceCoordinate> coords = new ArrayList<RaceCoordinate>();
		coords.add(new RaceCoordinate(lat, lng));
		return new RaceControlPoint(UUID.randomUUID().toString(), coords);
	}

	private List<RaceTrip> generateTrips() {
		List<RaceTrip> trips = new ArrayList<RaceTrip>();
		trips.add(new RaceTrip(UUID.randomUUID().toString(), "trip1", generateBoat("trip1-boatname"), generateWaypoints(0.0)));
		trips.add(new RaceTrip(UUID.randomUUID().toString(), "trip2", generateBoat("trip2-boatname"), generateWaypoints(0.1)));
		return trips;
	}

	private List<RaceWaypoint> generateWaypoints(Double posOffset) {
		List<RaceWaypoint> waypoints = new ArrayList<RaceWaypoint>();
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(), new RaceCoordinate(0.1 + posOffset, 0.3 + posOffset), 1000000000L, 2, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(), new RaceCoordinate(0.2 + posOffset, 0.4 + posOffset), 1000001000L, 3, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(), new RaceCoordinate(0.3 + posOffset, 0.5 + posOffset), 1000002000L, 4, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(), new RaceCoordinate(0.4 + posOffset, 0.6 + posOffset), 1000003000L, 4, null));
		waypoints.add(new RaceWaypoint(UUID.randomUUID().toString(), new RaceCoordinate(0.4 + posOffset, 0.7 + posOffset), 1000004000L, 3, null));
		return waypoints;
	}

	private RaceBoat generateBoat(String boatName) {
		return new RaceBoat(UUID.randomUUID().toString(), boatName, "GER");
	}

	@Override
	public boolean save(IRace data) {
		if (!db.containsKey(data)) {
			newRace = data;
		}
		db.put(data.getUUID(), data);
		return true;
	}

	@Override
	public IRace get(UUID id) {
		return new Race(db.get(id));
	}

	@Override
	public List<IRace> loadAll() {
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

}

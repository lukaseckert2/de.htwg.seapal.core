package de.htwg.seapal.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IRace;
import de.htwg.seapal.model.ModelDocument;

public class Race extends ModelDocument implements IRace {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String boatClass;
	private List<RaceTrip> trips;
	private List<RaceControlPoint> controlPoints;
	
	public Race() {
		setId(UUID.randomUUID().toString());
		this.name = "";
		this.boatClass = "";
		this.trips = new ArrayList<RaceTrip>();
		this.controlPoints = new ArrayList<RaceControlPoint>();
	}

	public Race(IRace race) {
		setId(race.getId());
		this.name = race.getName();
		this.boatClass = race.getBoatClass();
		this.trips = race.getTrips();
		this.controlPoints = race.getControlPoints();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getBoatClass() {
		return this.boatClass;
	}

	@Override
	public void setBoatClass(String boatClass) {
		this.boatClass = boatClass;
	}

	@Override
	public List<RaceTrip> getTrips() {
		return this.trips;
	}

	@Override
	public void setTrips(List<RaceTrip> trips) {
		this.trips = trips;
	}

	@Override
	public List<RaceControlPoint> getControlPoints() {
		return this.controlPoints;
	}

	@Override
	public void setControlPoints(List<RaceControlPoint> controlPoints) {
		this.controlPoints = controlPoints;
	}

}

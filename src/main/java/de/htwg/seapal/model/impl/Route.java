package de.htwg.seapal.model.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.ektorp.support.CouchDbDocument;

import de.htwg.seapal.model.IRoute;

public class Route extends CouchDbDocument implements IRoute {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	private String user; // UUID user

	private String name;
	private long date;
	private List<UUID> marks;
	private UUID routeEntryPoint;
	private double distance;

	public Route() {
		setId(UUID.randomUUID().toString());
		marks = new LinkedList<UUID>();
	}
	
	public Route(IRoute r) {
		setId(r.getId());
		marks = new LinkedList<UUID>();
		
		this.user = r.getUser();

		this.name = r.getName();
		this.date = r.getDate();
		this.marks = r.getMarks();
		this.routeEntryPoint = r.getRouteEntryPoint();
		this.distance = r.getDistance();
	}

	@Override
	public UUID getUUID() {
		return UUID.fromString(getId());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public long getDate() {
		return date;
	}

	@Override
	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public List<UUID> getMarks() {
		return marks;
	}

	@Override
	public void addMark(UUID mark) {
		this.marks.add(mark);
	}

	@Override
	public UUID getRouteEntryPoint() {
		return routeEntryPoint;
	}

	@Override
	public void setRouteEntryPoint(UUID mark) {
		this.routeEntryPoint = mark;
	}

	@Override
	public double getDistance() {
		return distance;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}
}

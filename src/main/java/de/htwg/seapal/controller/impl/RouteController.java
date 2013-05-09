package de.htwg.seapal.controller.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.text.format.DateFormat;
import de.htwg.seapal.controller.IRouteController;
import de.htwg.seapal.database.IRouteDatabase;
import de.htwg.seapal.model.IRoute;
import de.htwg.seapal.utils.observer.Observable;
import de.htwg.seapal.utils.logging.ILogger;

@Singleton
public class RouteController extends Observable implements IRouteController {

	private IRouteDatabase db;
	private final ILogger logger;

	@Inject
	public RouteController(IRouteDatabase db, ILogger logger) {
		this.db = db;
		this.logger = logger;
	}

	@Override
	public String getName(UUID id) {
		IRoute route = db.get(id);
		if (route == null)
			return null;
		return route.getName();
	}

	@Override
	public void setName(UUID id, String name) {
		IRoute route = db.get(id);
		if (route == null)
			return;
		route.setName(name);
		db.save(route);
		notifyObservers();
	}

	@Override
	public String getDate(UUID id) {
		IRoute route = db.get(id);
		if (route == null)
			return null;
		long currentMillis = route.getDate();
		return DateFormat.format("yyyy/MM/dd hh:mm", currentMillis).toString();
	}

	@Override
	public void setDate(UUID id, long date) {
		IRoute route = db.get(id);
		if (route == null)
			return;
		route.setDate(date);
		db.save(route);
		notifyObservers();
	}

	@Override
	public UUID getRouteEntryPoint(UUID id) {
		IRoute route = db.get(id);
		if (route == null)
			return null;
		return route.getRouteEntryPoint();
	}

	@Override
	public void setRouteEntryPoint(UUID id, UUID mark) {
		IRoute route = db.get(id);
		if (route == null)
			return;
		route.setRouteEntryPoint(mark);
		db.save(route);
		notifyObservers();
	}

	@Override
	public double getDistance(UUID id) {
		IRoute route = db.get(id);
		if (route == null)
			return -1;
		return route.getDistance();
	}

	@Override
	public void setDistance(UUID id, double distance) {
		IRoute route = db.get(id);
		if (route == null)
			return;
		route.setDistance(distance);
		db.save(route);
		notifyObservers();

	}

	@Override
	public List<UUID> getMarks(UUID id) {
		IRoute route = db.get(id);
		if (route == null)
			return null;
		return route.getMarks();
	}

	@Override
	public void addMark(UUID id, UUID mark) {
		IRoute route = db.get(id);
		if (route == null)
			return;
		route.addMark(mark);
		db.save(route);
		notifyObservers();
	}

	@Override
	public void deleteRoute(UUID id) {
		db.delete(id);
		notifyObservers();
	}

	@Override
	public void closeDB() {
		db.close();

	}

	@Override
	public List<UUID> getRoutes() {
		List<UUID> list = new LinkedList<UUID>();
		List<IRoute> routes = db.getAll();
		for (IRoute entry : routes) {
			list.add(entry.getUUID());
		}
		return list;
	}

	@Override
	public UUID newRoute() {
		UUID newRoute = db.create();
		setDate(newRoute, System.currentTimeMillis());
		notifyObservers();
		return newRoute;
	}

	@Override
	public String getString(UUID id) {
		return "ID = \t" + id + "\nName = \t" + getName(id) + "\nDistance = \t"
				+ getDistance(id) + "\nDate = \t" + getDate(id)
				+ "\nRouteEntryPoint = \t" + getRouteEntryPoint(id);
	}

}

package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IRouteDatabase;
import de.htwg.seapal.model.IRoute;
import de.htwg.seapal.model.impl.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RouteDatabase implements IRouteDatabase {
	Map<UUID, IRoute> db = new HashMap<UUID, IRoute>();
	private IRoute newRoute;

	public RouteDatabase() {
		open();
	}

	@Override
	public boolean open() {
		// create test data
		UUID id = createNewRouteInDatabase();
		newRoute = get(id);
		newRoute.setName("Route-NEW");
		save(newRoute);
		for (int i = 1; i < 10; i++) {
			id = createNewRouteInDatabase();
			IRoute waypoint = get(id);
			waypoint.setName("Route-" + i);
			save(waypoint);
		}
		return true;
	}

	@Override
	public boolean close() {
		return true;
	}
    @Override
    public List<? extends IRoute> queryViews(final String viewName, final String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public UUID create() {
		return newRoute.getUUID();
	}

	private UUID createNewRouteInDatabase() {
		IRoute route = new Route();
		UUID id = route.getUUID();
		db.put(id, route);
		return id;
	}

	@Override
	public boolean save(IRoute route) {
		return true;
	}

	@Override
	public void delete(UUID id) {
	}

	@Override
	public IRoute get(UUID id) {
		return new Route(db.get(id));
	}

	@Override
	public List<IRoute> loadAll() {
		return ImmutableList.copyOf(db.values());
	}
}

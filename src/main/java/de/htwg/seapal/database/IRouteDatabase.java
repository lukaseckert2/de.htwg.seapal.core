package de.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import de.htwg.seapal.model.IRoute;

public interface IRouteDatabase extends IDatabase {

	/**
	 * Saves a route.
	 * @param route The route to save.
	 */
	void save(IRoute route);

	/**
	 * Gets a route with a given UUID.
	 * @param id The UUID of the route.
	 * @return The route with the given UUID or NULL,
	 *         if no route was found.
	 */
	IRoute get(UUID id);

	/**
	 * Gets a list of all routes.
	 * @return All routes.
	 */
	List<IRoute> getAll();
}

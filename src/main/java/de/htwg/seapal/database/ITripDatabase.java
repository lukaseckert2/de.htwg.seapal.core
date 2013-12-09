package de.htwg.seapal.database;

import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.impl.Trip;
import org.ektorp.support.GenerateView;

import java.util.List;
import java.util.UUID;

/**
 * The trip database interface.
 * <p>
 * Note: all methods which use a generated view must:
 * 		 - generated view name: by_[property]
 *       - be named: findBy[Property]
 *       - only have 1 param
 *       - [Property] must exist in target class (by_color -> e.g. public String getColor())
 *       - iterable fields: plural -> getColors()
 *       - initStandardDesignDocument() of CouchDbRepositorySupport must be triggered
 * Remark: the design file "_design/Trip" must be created manually.
 * </p>
 */
public interface ITripDatabase extends IDatabase<ITrip> {
	/**
	 * Gets all trips by the given boat UUID.
	 * @param boatId The boats UUID.
	 * @return All matching trips.
	 */
	@GenerateView
    List<ITrip> findByBoat(UUID boatId);

    public List<Trip> getTrips(String key, String viewId);
}

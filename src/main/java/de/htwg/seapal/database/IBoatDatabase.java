package de.htwg.seapal.database;

import de.htwg.seapal.model.IBoat;

import java.util.List;

/**
 * The boat database interface.
 * <p>
 * Note: all methods which use a generated view must:
 * 		 - generated view name: by_[property]
 *       - be named: findBy[Property]
 *       - only have 1 param
 *       - [Property] must exist in target class (by_color -> e.g. public String getColor())
 *       - iterable fields: plural -> getColors()
 *       - initStandardDesignDocument() of CouchDbRepositorySupport must be triggered
 * Remark: the design file "_design/Boat" must be created manually.
 * </p>
 */
public interface IBoatDatabase extends IDatabase<IBoat> {
    List<de.htwg.seapal.model.impl.Boat> getBoats(String userId, String viewId);
}

package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;

import de.htwg.seapal.database.IWaypointDatabase;
import de.htwg.seapal.model.IWaypoint;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Waypoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WaypointDatabase implements IWaypointDatabase {

	private final Map<UUID, IWaypoint> db = new HashMap<>();
	private IWaypoint newWaypoint;

	public WaypointDatabase() {
		open();
	}

	@Override
	public boolean open() {
		// create test data
		UUID id = createNewWaypointInDatabase();
		newWaypoint = get(id);
		newWaypoint.setName("Waypoint-NEW");
		save(newWaypoint);
		for (int i = 1; i < 10; i++) {
			id = createNewWaypointInDatabase();
			IWaypoint waypoint = get(id);
			waypoint.setName("Waypoint-" + i);
			save(waypoint);
		}
		return true;
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
    public List<? extends IWaypoint> queryViews(final String viewName, final String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ModelDocument document) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public UUID create() {
		return newWaypoint.getUUID();
	}

	private UUID createNewWaypointInDatabase() {
		IWaypoint waypoint = new Waypoint();
		UUID id = waypoint.getUUID();
		db.put(id, waypoint);
		return id;
	}

	@Override
	public boolean save(IWaypoint waypoint) {
		return true;
	}

	@Override
	public void delete(UUID id) {
	}

	@Override
	public IWaypoint get(UUID id) {
		return new Waypoint(db.get(id));
	}

	@Override
	public List<IWaypoint> loadAll() {
		return ImmutableList.copyOf(db.values());
	}

    @Override
    public String addPhoto(IWaypoint mark, String contentType, File file) throws FileNotFoundException {
        return "";
    }

    @Override
    public InputStream getPhoto(UUID uuid) {
        return null;
    }

	@Override
	public List<WaypointPictureBean> getPhotosByTripId(UUID tripId,
			int startIndex, int count) {
		
		return null;
	}

	@Override
	public List<? extends IWaypoint> getWaypointsByTripId(UUID tripId,
			int startIndex, int count) {
		
		return new ArrayList<>(db.values());
	}

	@Override
	public List<? extends IWaypoint> getWaypointsOfTripSlim(UUID tripId) {
		return new ArrayList<>(db.values());
	}
}

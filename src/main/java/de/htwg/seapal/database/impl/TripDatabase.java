package de.htwg.seapal.database.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;

import com.google.inject.Inject;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.impl.Trip;

public class TripDatabase implements ITripDatabase {

	private final Logger log = Logger.getLogger(TripDatabase.class);
	
	private CouchDbConnector db;
	
	@Inject
	protected TripDatabase(CouchDbConnector db) {
		log.info("Database instance: " + db);
		this.db = db;
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(ITrip data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITrip get(UUID id) {
		return db.get(Trip.class, id.toString() );
	}

	@Override
	public List<ITrip> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}

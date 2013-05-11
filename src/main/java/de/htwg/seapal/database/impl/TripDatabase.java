package de.htwg.seapal.database.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

import com.google.inject.Inject;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.impl.Trip;

public class TripDatabase extends CouchDbRepositorySupport<Trip> implements
		ITripDatabase {

	@Inject
	protected TripDatabase(CouchDbConnector db) {
		super(Trip.class, db);
	}

	@Override
	public boolean open() {
		return false;
	}

	@Override
	public UUID create() {
		return null;
	}

	@Override
	public boolean save(ITrip data) {
		add((Trip) data);

		return false;
	}

	@Override
	public Trip get(UUID id) {
		return get(id.toString());
	}

	@Override
	public List<ITrip> loadAll() {
		return new LinkedList<ITrip>(getAll());
	}

	@Override
	public void delete(UUID id) {
		remove(get(id));
	}

	@Override
	public boolean close() {
		return false;
	}

}

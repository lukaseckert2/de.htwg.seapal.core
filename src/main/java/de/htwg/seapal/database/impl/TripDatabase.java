package de.htwg.seapal.database.impl;

import java.util.List;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

import com.google.inject.Inject;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.impl.Trip;

@View( name="all", map = "function(doc) { if (doc.blogPostId) { emit(null, doc) } }")
public class TripDatabase extends CouchDbRepositorySupport<Trip> implements ITripDatabase {

	@Inject
	protected TripDatabase(CouchDbConnector db) {
		super(Trip.class, db);
		System.out.println(db);
		// TODO Auto-generated constructor stub
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
	public void delete(UUID id) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ITrip trip) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Trip get(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ITrip> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

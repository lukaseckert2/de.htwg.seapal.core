package de.htwg.seapal.database.impl;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.impl.Trip;

public class TripDatabaseTest {
	
	ITripDatabase<Trip> tripDatabase;
	
	Logger log = Logger.getLogger(TripDatabaseTest.class);

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new TripModule());
		this.tripDatabase = injector.getInstance(TripDatabase.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		log.debug("test");
		assert(false);
	}

}

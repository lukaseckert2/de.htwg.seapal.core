package de.htwg.seapal.database.impl;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.model.ITrip;

public class TripDatabaseTest {
	
	ITripDatabase tripDatabase;
	
	Logger log = Logger.getLogger(TripDatabaseTest.class);

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new TripModule());
		this.tripDatabase = injector.getInstance(ITripDatabase.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		ITrip trip = tripDatabase.get( UUID.fromString("234033c2-f2b7-4a4a-aba7-67d4eee3884c"));
		log.info(trip);
		assertTrue("Trip is not here?", trip != null);
	}
	
	@Test
	public void testsdfsdf() {
		
	}

}

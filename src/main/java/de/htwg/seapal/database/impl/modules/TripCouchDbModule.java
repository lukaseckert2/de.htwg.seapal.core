package de.htwg.seapal.database.impl.modules;

import com.google.inject.name.Names;

public class TripCouchDbModule extends CouchDbModule {
	private static final String SEAPAL_TRIPS_DB = "seapal_trips_db";

	@Override
	protected void configure() {
		super.configure();
		bind(String.class).annotatedWith(Names.named("databaseName"))
				.toInstance(SEAPAL_TRIPS_DB);
	}
}

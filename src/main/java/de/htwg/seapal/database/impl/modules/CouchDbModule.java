package de.htwg.seapal.database.impl.modules;

import java.net.MalformedURLException;

import org.ektorp.CouchDbConnector;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import de.htwg.seapal.database.ITripDatabase;
import de.htwg.seapal.database.impl.TripDatabase;

public class CouchDbModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("databaseURL"))
				.toInstance("http://roroettg.iriscouch.com");
		bind(ITripDatabase.class).to(TripDatabase.class);
		bind(CouchDbConnector.class).to(StdCouchDbConnector.class);
	}

	@Provides
	HttpClient getHttpClient(@Named("databaseURL") String databaseHost) {
		try {
			return new StdHttpClient.Builder().url(databaseHost).build();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Provides
	StdCouchDbInstance getStdCouchDbInstance(HttpClient httpClient) {
		return new StdCouchDbInstance(httpClient);
	}

	@Provides
	StdCouchDbConnector getStdCouchDbConnector(
			@Named("databaseName") String databaseName,
			StdCouchDbInstance stdCouchDbInstance) {
		return new StdCouchDbConnector(databaseName, stdCouchDbInstance);
	}

}

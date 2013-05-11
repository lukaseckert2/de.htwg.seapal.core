package de.htwg.seapal.model;

import java.util.List;
import java.util.UUID;

public interface IRegatta extends IModel {
	String getName();
	void setName(String name);

	String getHost();
	void setHost(String host);

	long getEstimatedStartDate();
	void setEstimatedStartDate(long date);

	long getEstimatedFinishTime();
	void setEstimatedFinishTime(long date);

	long getRealStartTime();
	void setRealStartTime(long date);

	long getRealFinishTime();
	void setRealFinishTime(long date);
	
	String getBoat();
	void setBoat(String boat);
	
	List<String> getTrips();
	void addTrip(String tripId);

	String getString();
}

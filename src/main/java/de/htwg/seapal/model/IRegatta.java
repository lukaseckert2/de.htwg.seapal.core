package de.htwg.seapal.model;

import java.util.List;
import java.util.UUID;

public interface IRegatta extends IModel {
	String getName();
	void setName(String name);
	
	void addRace(UUID raceId);
	List<UUID> getRaces();
}

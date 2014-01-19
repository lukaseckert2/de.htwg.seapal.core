package de.htwg.seapal.controller;

import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.UUID;

public interface IPersonController extends IObservable {

	void closeDB();

	void deletePerson(UUID personId);

	List<UUID> getPersons();

	UUID newPerson();

	IPerson getPerson(UUID personId);

	boolean savePerson(IPerson person);

    List<? extends IPerson> queryView(String viewName, String key);

    IPerson getByAccount(UUID uuid);
}

package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IPersonController;
import de.htwg.seapal.database.IPersonDatabase;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonController extends Observable implements IPersonController {

	protected IPersonDatabase db;
	private final ILogger logger;

	@Inject
	public PersonController(IPersonDatabase db, ILogger logger) {
		this.db = db;
		this.logger = logger;
	}

	@Override
	public List<UUID> getPersons() {
		List<IPerson> persons = db.loadAll();
		List<UUID> list = new ArrayList<UUID>();
		for (IPerson person : persons) {
			list.add(person.getUUID());
		}
		return list;
	}

    @Override
    public UUID newPerson() {
        return db.create();
    }

    @Override
	public final void closeDB() {
		db.close();
		logger.info("PersonController", "Database closed");
	}

    @Override
    public void deletePerson(UUID personId) {
        db.delete(personId);
    }

    @Override
	public IPerson getPerson(UUID personId) {
		return db.get(personId);
	}

	@Override
	public boolean savePerson(IPerson person) {
		return db.save(person);
	}
    @Override
    public List<? extends IPerson> queryView(final String viewName, final String key) {
        return db.queryViews(viewName, key);
    }

    @Override
    public IPerson getByAccount(UUID uuid) {
        List<? extends IPerson> persons = queryView("own", uuid.toString());
        if (persons.size() > 1 || persons.size() < 1) {
            return null;
        }

        return persons.get(0);
    }
}

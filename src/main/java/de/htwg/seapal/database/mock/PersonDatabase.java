package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IPersonDatabase;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PersonDatabase implements IPersonDatabase {

    private final Map<UUID, IPerson> db = new HashMap<>();
    private IPerson newPerson;

	public PersonDatabase() {
		open();
	}

	private UUID createNewPersonInDatabase() {
		IPerson person = new Person();
		UUID id = person.getUUID();
		db.put(id, person);
		return id;
	}

	@Override
	public boolean open() {
		// create test data
		UUID id = createNewPersonInDatabase();
		newPerson = get(id);
		newPerson.setFirstname("Firstname-NEW");
		newPerson.setLastname("Firstname-NEW");
		save(newPerson);
		for (int i = 1; i < 10; i++) {
			id = createNewPersonInDatabase();
			IPerson person = get(id);
			person.setFirstname("Firstname-" + i);
			person.setLastname("Firstname-" + i);
			save(person);
		}
		return true;
	}

	@Override
	public boolean close() {
		return true;
	}

    @Override
    public void create(ModelDocument document) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<? extends IPerson> queryViews(final String viewName, final String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ModelDocument document) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Person getAccount(final String email)
            throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public void delete(UUID id) {
	}

	@Override
	public IPerson get(UUID id) {
		return new Person(db.get(id));
	}

	@Override
	public List<IPerson> loadAll() {
		return ImmutableList.copyOf(db.values());
	}

	@Override
	public UUID create() {
		return newPerson.getUUID();
	}

	@Override
	public boolean save(IPerson person) {
		return true;
	}
}

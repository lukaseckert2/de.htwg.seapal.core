package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IPersonDatabase;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Person;

import java.util.*;

public class PersonDatabase implements IPersonDatabase {
    private final Map<UUID, IPerson> db = new HashMap<>();

    public PersonDatabase() {
        open();
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public void create(ModelDocument document) {
        document.setRevision("1");
        db.put(document.getUUID(), (IPerson) document);
    }

    @Override
    public List<? extends IPerson> queryViews(final String viewName, final String key) {
        List<IPerson> list = new LinkedList<>();
        if (viewName.equals("singleDocument")) {
            for (IPerson person : db.values()) {
                if (key.equals(person.getAccount() + person.getId())) {
                    list.add(person);
                }
            }
        } else if (viewName.equals("own")) {
            for (IPerson person : db.values()) {
                if (key.equals(person.getAccount())) {
                    list.add(person);
                }
            }
        } else {
            throw new RuntimeException("method not implemented");
        }
        return list;
    }

    @Override
    public void update(ModelDocument document) {
        db.put(document.getUUID(), (IPerson) document);
    }

    @Override
    public UUID create() {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public boolean save(IPerson person) {
        db.put(person.getUUID(), person);
        return true;
    }

    @Override
    public void delete(UUID id) {
        db.remove(id);
    }

    @Override
    public IPerson get(UUID id) {
        IPerson person = db.get(id);
        if (person != null)
            return new Person(person);
        return null;
    }

    @Override
    public List<IPerson> loadAll() {
        return ImmutableList.copyOf(db.values());
    }
}

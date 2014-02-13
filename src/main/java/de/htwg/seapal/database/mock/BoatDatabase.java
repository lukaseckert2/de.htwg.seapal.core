package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IBoatDatabase;
import de.htwg.seapal.model.IBoat;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Boat;

import java.util.*;

public class BoatDatabase implements IBoatDatabase {

    private final Map<UUID, IBoat> db = new HashMap<>();

    public BoatDatabase() {
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
        db.put(document.getUUID(), (IBoat) document);
    }

    @Override
    public List<? extends IBoat> queryViews(final String viewName, final String key) {
        List<IBoat> list = new LinkedList<>();
        if (viewName.equals("singleDocument")) {
            for (IBoat boat : db.values()) {
                if (key.equals(boat.getAccount() + boat.getId())) {
                    list.add(boat);
                }
            }
        } else if (viewName.equals("own")) {
            for (IBoat boat : db.values()) {
                if (key.equals(boat.getAccount())) {
                    list.add(boat);
                }
            }
        } else {
            throw new RuntimeException("method not implemented");
        }
        return list;
    }

    @Override
    public void update(ModelDocument document) {
        db.put(document.getUUID(), (IBoat) document);
    }

    @Override
    public UUID create() {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public boolean save(IBoat boat) {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public void delete(UUID id) {
        db.remove(id);
    }

    @Override
    public IBoat get(UUID id) {
        IBoat boat = db.get(id);
        if (boat != null)
            return new Boat(boat);
        return null;
    }

    @Override
    public List<IBoat> loadAll() {
        return ImmutableList.copyOf(db.values());
    }
}

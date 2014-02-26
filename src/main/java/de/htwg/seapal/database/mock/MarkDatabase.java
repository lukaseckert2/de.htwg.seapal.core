package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IMarkDatabase;
import de.htwg.seapal.model.IMark;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Mark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class MarkDatabase implements IMarkDatabase {

    private final Map<UUID, IMark> db = new HashMap<>();
    private final Map<UUID, File> db2 = new HashMap<>();

    public MarkDatabase() {
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
        db.put(document.getUUID(), (IMark) document);
    }

    @Override
    public List<? extends IMark> queryViews(final String viewName, final String key) {
        List<IMark> list = new LinkedList<>();
        if (viewName.equals("singleDocument")) {
            for (IMark mark : db.values()) {
                if (key.equals(mark.getAccount() + mark.getId())) {
                    list.add(mark);
                }
            }
        } else if (viewName.equals("own")) {
            for (IMark mark : db.values()) {
                if (key.equals(mark.getAccount())) {
                    list.add(mark);
                }
            }
        } else {
            throw new RuntimeException("method not implemented");
        }
        return list;
    }

    @Override
    public void update(ModelDocument document) {
        db.put(document.getUUID(), (IMark) document);
    }

    @Override
    public UUID create() {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public boolean save(IMark mark) {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public void delete(UUID id) {
        db.remove(id);
    }

    @Override
    public IMark get(UUID id) {
        IMark mark = db.get(id);
        if (mark != null)
            return new Mark(mark);
        return null;
    }

    @Override
    public List<IMark> loadAll() {
        return ImmutableList.copyOf(db.values());
    }

    @Override
    public String addPhoto(IMark mark, String contentType, File file) throws FileNotFoundException {
        db2.put(mark.getUUID(), file);
        return "";
    }

    @Override
    public InputStream getPhoto(UUID uuid) {
        try {
            return new FileInputStream(db2.get(uuid));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

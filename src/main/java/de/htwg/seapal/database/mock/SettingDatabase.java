package de.htwg.seapal.database.mock;

import de.htwg.seapal.database.ISettingDatabase;
import de.htwg.seapal.model.ISetting;
import de.htwg.seapal.model.ModelDocument;

import java.util.List;
import java.util.UUID;

public final class SettingDatabase implements ISettingDatabase {
    @Override
    public boolean open() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public UUID create() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean save(ISetting data) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public ISetting get(UUID id) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<ISetting> loadAll() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void delete(UUID id) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean close() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void create(ModelDocument document) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<? extends ISetting> queryViews(String viewName, String key) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void update(ModelDocument document) {
        throw new RuntimeException("not implemented");
    }
}

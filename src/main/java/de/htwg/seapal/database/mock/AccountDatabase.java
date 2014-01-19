package de.htwg.seapal.database.mock;

import de.htwg.seapal.database.IAccountDatabase;
import de.htwg.seapal.model.IAccount;

import java.util.List;
import java.util.UUID;

public final class AccountDatabase implements IAccountDatabase {
    @Override
    public boolean open() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UUID create() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean save(IAccount data) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IAccount get(UUID id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<IAccount> loadAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(UUID id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean close() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<? extends IAccount> queryViews(String viewName, String key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IAccount getAccount(String email) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

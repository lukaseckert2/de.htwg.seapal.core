package de.htwg.seapal.database.mock;

import com.google.common.collect.ImmutableList;
import de.htwg.seapal.database.IAccountDatabase;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Account;

import java.util.*;

public final class AccountDatabase implements IAccountDatabase {

    private final Map<UUID, IAccount> db = new HashMap<>();

    public AccountDatabase() {
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
        db.put(document.getUUID(), (IAccount) document);
    }

    @Override
    public List<? extends IAccount> queryViews(final String viewName, final String key) {
        List<IAccount> list = new LinkedList<>();
        if (viewName.equals("singleDocument")) {
            for (IAccount account : db.values()) {
                if (key.equals(account.getAccount() + account.getId())) {
                    list.add(account);
                }
            }
        } else if (viewName.equals("own")) {
            for (IAccount account : db.values()) {
                if (key.equals(account.getAccount())) {
                    list.add(account);
                }
            }
        } else if (viewName.equals("by_email")) {
            for (IAccount account : db.values()) {
                if (key.equals(account.getEmail())) {
                    list.add(account);
                }
            }
        } else {
            throw new RuntimeException(viewName + " method not implemented");
        }
        return list;
    }

    @Override
    public void update(ModelDocument document) {
        db.put(document.getUUID(), (IAccount) document);
    }

    @Override
    public UUID create() {
        throw new RuntimeException("method not implemented");
    }

    @Override
    public boolean save(IAccount account) {
        db.put(account.getUUID(), account);
        return true;
    }

    @Override
    public void delete(UUID id) {
        db.remove(id);
    }

    @Override
    public IAccount get(UUID id) {
        IAccount account = db.get(id);
        if (account != null)
            return new Account(account);
        return null;
    }

    @Override
    public List<IAccount> loadAll() {
        return ImmutableList.copyOf(db.values());
    }

    @Override
    public IAccount getAccount(String email) {
        for (IAccount account : db.values()) {
            if (email.equals(account.getEmail())) {
                return account;
            }
        }

        return null;
    }
}

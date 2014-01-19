package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IPersonController;
import de.htwg.seapal.database.IAccountDatabase;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.impl.Account;
import de.htwg.seapal.model.impl.Person;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class AccountController extends Observable implements IAccountController {
    protected IAccountDatabase db;
    private final ILogger logger;

    @Inject
    private IPersonController controller;

    @Inject
    public AccountController(IAccountDatabase db, ILogger logger) {
        this.db = db;
        this.logger = logger;
    }

    @Override
    public void closeDB() {
        db.close();
        logger.info("AccountController", "Database closed");
    }

    @Override
    public void deleteAccount(UUID AccountId) {
        db.delete(AccountId);
    }

    @Override
    public List<UUID> getAccounts() {
        List<IAccount> persons = db.loadAll();
        List<UUID> list = new ArrayList<UUID>();
        for (IAccount person : persons) {
            list.add(person.getUUID());
        }
        return list;
    }

    @Override
    public UUID newAccount() {

        return db.create();
    }

    @Override
    public IAccount getAccount(UUID AccountId) {
        return db.get(AccountId);
    }

    @Override
    public List<IAccount> getAllAccounts() {
        return db.loadAll();
    }

    @Override
    public boolean saveAccount(IAccount Account) {
        boolean returnVal = db.save(Account);

        IPerson person = new Person();
        person.setAccount(Account.getId());

        return returnVal && controller.savePerson(person);
    }

    @Override
    public List<? extends IAccount> queryView(String viewName, String key) {
        return db.queryViews(viewName, key);
    }

    @Override
    public IAccount authenticate(final IAccount account)
            throws Exception {
        IAccount savedAccount = db.getAccount(account.getEmail());

        if (savedAccount != null && PasswordHash.validatePassword(account.getPassword(), savedAccount.getPassword())) {
            return savedAccount;
        }

        return null;
    }

    @Override
    public boolean accountExists(final String email) {
        List<? extends IAccount> accounts = db.queryViews("by_email", email);
        return accounts.size() > 0;
    }

    @Override
    public IAccount googleLogin(final Map<String, String> userInfo, final String googleID) {
        List<? extends IAccount> accounts = db.queryViews("googleID", googleID);
        if (accounts.size() > 0) {
            // Account exists and is connected to Google Account
            return accounts.get(0);
        }

        accounts = db.queryViews("by_email", userInfo.get("Email"));
        if (accounts.size() > 0) {
            // Account exists, but is not connected to Google Account
            IAccount person = accounts.get(0);
            person.setGoogleID(googleID);
            saveAccount(person);
            return saveAccount(person) ? person : null;
        }


        IAccount account = new Account();
        account.setGoogleID(googleID);
        account.setEmail(userInfo.get("Email"));
        saveAccount(account);

        return account;
    }

    @Override
    public IPerson getPerson(UUID uuid) {
        return controller.getByAccount(uuid);
    }

    @Override
    public PublicPerson getInternalInfo(String session) {
        return new PublicPerson(getAccount(UUID.fromString(session)));
    }
}

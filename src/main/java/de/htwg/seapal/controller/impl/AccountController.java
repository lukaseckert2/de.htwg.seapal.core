package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IPersonController;
import de.htwg.seapal.database.IAccountDatabase;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.impl.Account;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.model.impl.SignupAccount;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class AccountController extends Observable implements IAccountController {
    private final IAccountDatabase db;
    private final ILogger logger;

    @Inject
    private IPersonController controller;

    @Inject
    public AccountController(IAccountDatabase db, ILogger logger) {
        this.db = db;
        this.logger = logger;
    }

    private IAccount getAccount(UUID AccountId) {
        return db.get(AccountId);
    }

    @Override
    public UUID saveAccount(SignupAccount account, boolean createHash) {
        if (createHash)
            try {
                String hash = PasswordHash.createHash(account.getPassword());
                account.setPassword(hash);
            } catch (Exception e) {
                logger.exc(e);
                return null;
            }

        // side effects!
        if (db.save(account.getAccount())) {
            controller.savePerson(account.getPerson());
        }

        return account.getId();
    }

    @Override
    public List<? extends IAccount> queryView(String viewName, String key) {
        return db.queryViews(viewName, key);
    }

    @Override
    public IAccount authenticate(final IAccount account) {
        IAccount savedAccount = db.getAccount(account.getEmail());

        try {
            if (savedAccount != null && PasswordHash.validatePassword(account.getPassword(), savedAccount.getPassword())) {
                return savedAccount;
            }
        } catch (Exception e) {
            logger.exc(e);
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

        accounts = db.queryViews("by_email", userInfo.get(KEY_EMAIL));
        if (accounts.size() > 0) {
            // Account exists, but is not connected to Google Account
            IAccount person = accounts.get(0);
            person.setGoogleID(googleID);
            return saveAccount(person) ? person : null;
        }


        IAccount account = new Account();
        account.setGoogleID(googleID);
        account.setEmail(userInfo.get(KEY_EMAIL));
        saveAccount(new SignupAccount(account, userInfo.get(KEY_FIRST_NAME), userInfo.get(KEY_LAST_NAME)), false);

        return account;
    }

    private boolean saveAccount(IAccount person) {
        return db.save(person);
    }

    @Override
    public PublicPerson getInternalInfo(String session) {
        return new PublicPerson(getAccount(UUID.fromString(session)));
    }

    @Override
    public UUID saveAccount(IAccount account, boolean createHash) {
        if (createHash)
            try {
                account.setPassword(PasswordHash.createHash(account.getPassword()));
            } catch (Exception e) {
                logger.exc(e);
            }

        // side effects!
        db.save(account);

        return UUID.fromString(account.getId());
    }
}

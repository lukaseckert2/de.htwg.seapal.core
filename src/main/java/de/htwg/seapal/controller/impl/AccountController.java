package de.htwg.seapal.controller.impl;

import com.google.inject.Inject;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.database.IAccountDatabase;
import de.htwg.seapal.database.IPersonDatabase;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.impl.Account;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.model.impl.SignupAccount;
import de.htwg.seapal.utils.logging.ILogger;
import de.htwg.seapal.utils.observer.Observable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * AccountController is the other interface between database and view layer. It complements the interface in
 * MainController. This class cares more about the accounts and AuthN/Z, MainController cares more about the documents
 * which belong the the accounts.
 */
public final class AccountController extends Observable implements IAccountController {

    @Inject
    private IAccountDatabase db;

    @Inject
    private ILogger logger;

    @Inject
    private IPersonDatabase controller;

    @Inject
    public AccountController() {
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
            controller.save(account.getPerson());
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

    /**
     * check if there is an exisiting account for this email address.
     *
     * @param email the email address to be checked.
     * @return true, if the account exists.
     */
    @Override
    public boolean accountExists(final String email) {
        List<? extends IAccount> accounts = db.queryViews("by_email", email);
        return accounts.size() > 0;
    }

    /**
     * perform a login with Google. If there is already an account with the email address of this Google Account, it
     * connects the Google ID to the existing account. If the account
     *
     * @param userInfo
     * @param googleID
     * @return
     */
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
            return db.save(person) ? person : null;
        }


        IAccount account = new Account();
        account.setGoogleID(googleID);
        account.setEmail(userInfo.get(KEY_EMAIL));
        saveAccount(new SignupAccount(account, userInfo.get(KEY_FIRST_NAME), userInfo.get(KEY_LAST_NAME)), false);

        return account;
    }

    @Override
    public PublicPerson getInternalInfo(String session, String userid) {
        IAccount account = db.get(UUID.fromString(userid));
        if (account != null && (session.equals(userid) || account.getFriendList().contains(session))) {
            return new PublicPerson(db.get(UUID.fromString(session)));
        } else {
            return null;
        }
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

package de.htwg.seapal.controller;

import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IAccountController extends IObservable {
    String AUTHN_COOKIE_KEY = "id";
    String KEY_EMAIL = "email";
    String KEY_FIRST_NAME = "first_name";
    String KEY_LAST_NAME = "last_name";

    void closeDB();

    void deleteAccount(UUID AccountId);

    List<UUID> getAccounts();

    UUID newAccount();

    IAccount getAccount(UUID AccountId);

    List<IAccount> getAllAccounts();

    boolean saveAccount(IAccount Account, boolean createHash);

    List<? extends IAccount> queryView(String viewName, String key);

    IAccount authenticate(IAccount account);

    boolean accountExists(String email);

    IAccount getByMail(String email);

    IAccount googleLogin(Map<String, String> userInfo, String googleID);

    IPerson getPerson(UUID uuid);

    PublicPerson getInternalInfo(String session);
}

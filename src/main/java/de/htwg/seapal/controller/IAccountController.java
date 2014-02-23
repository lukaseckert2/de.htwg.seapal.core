package de.htwg.seapal.controller;

import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.impl.PublicPerson;
import de.htwg.seapal.model.impl.SignupAccount;
import de.htwg.seapal.utils.observer.IObservable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IAccountController extends IObservable {
    String AUTHN_COOKIE_KEY = "id";
    String KEY_EMAIL = "email";
    String KEY_FIRST_NAME = "first_name";
    String KEY_LAST_NAME = "last_name";

    UUID saveAccount(SignupAccount Account, boolean createHash);

    List<? extends IAccount> queryView(String viewName, String key);

    IAccount authenticate(IAccount account);

    boolean accountExists(String email);

    IAccount googleLogin(Map<String, String> userInfo, String googleID);

    PublicPerson getInternalInfo(String session, String userid);

    UUID saveAccount(IAccount account, boolean createHash);
}

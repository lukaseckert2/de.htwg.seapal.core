package de.htwg.seapal.database;

import de.htwg.seapal.model.IAccount;

public interface IAccountDatabase extends IDatabase<IAccount>{
    IAccount getAccount(String email);
}

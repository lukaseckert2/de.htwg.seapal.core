package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IPerson;

import java.util.UUID;

/**
 * this object represents the POST data of the signup form. it won't be saved as it is, but it is needed to parse the
 * information out of the request the user sends, when he hits "Signup" with a filled form. After the JSON object is
 * parsed, the controller creates the Account object out of the SignupAccount object, hashes the password and saves the
 * Account object.
 */
public final class SignupAccount {
    private String email;
    private String password;
    private UUID id;
    private String firstName;
    private String lastName;

    private IAccount account = null;

    public SignupAccount() {}

    public SignupAccount(IAccount account, String s, String s1) {
        this.account = account;
        this.firstName = s;
        this.lastName = s1;
        this.password = account.getPassword();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public IAccount getAccount() {
        if (this.account != null) {
            this.id = account.getUUID();
            return this.account;
        } else {
            IAccount account = new Account();
            account.setPassword(password);
            account.setEmail(email);
            this.id = account.getUUID();
            return account;
        }
    }

    public void setPassword(String password) {
        this.password = password;
        if (this.account != null) {
            this.account.setPassword(password);
        }
    }

    public IPerson getPerson() {
        IPerson person = new Person();
        person.setAccount(id.toString());
        person.setFirstname(firstName);
        person.setLastname(lastName);
        return person;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }

        return email;
    }
}

package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.ModelDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Account extends ModelDocument implements IAccount {
    private String password = null;

    private String token = null;

    private long timeout = 0L;

    private String googleID = null;

    private List<String> friendList = new ArrayList<>();

    private List<String> sentRequests = new ArrayList<>();

    private List<String> receivedRequests = new ArrayList<>();
    private String email;

    public Account() {
        super(UUID.randomUUID().toString());
        password = "";
        token = "";
        timeout = 0L;
        googleID = "";
        email = "";
    }

    public Account(IAccount account) {
        super(account);
        password = account.getPassword();
        token = account.getResetToken();
        timeout = account.getResetTimeout();
        friendList = account.getFriendList();
        sentRequests = account.getSentRequests();
        receivedRequests = account.getReceivedRequests();
        googleID = account.getGoogleID();
        email = account.getEmail();
    }

    @Override
    public List<String> getFriendList() {
        return friendList;
    }

    @Override
    public void setFriendList(final List<String> friendList) {
        this.friendList = friendList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public void setResetToken(final String token) {
        this.token = token;
    }

    @Override
    public String getResetToken() {
        return token;
    }

    @Override
    public void setResetTimeout(final long timeout) {
        this.timeout = timeout;
    }

    @Override
    public long getResetTimeout() {
        return this.timeout;
    }

    @Override
    public List<String> getSentRequests() {
        return this.sentRequests;
    }

    @Override
    public void getRequestFromAccount(final List<String> setList) {
        this.sentRequests = setList;
    }

    @Override
    public List<String> getReceivedRequests() {
        return this.receivedRequests;
    }

    @Override
    public void getRequestToAccount(final List<String> setList) {
        this.receivedRequests = setList;
    }

    @Override
    public String getGoogleID() {
        return this.googleID;
    }

    @Override
    public void setGoogleID(final String openID) {
        this.googleID = openID;
    }

    /**
     * the instance calling addFriend wants to add askedPerson to his friendList.
     * friendList contains approved friends
     * sentRequests contains uuids I have sent requests to
     * receivedRequests contains uuids I have received requests from
     *
     * this method should be in AccountController, but it is easier to read, if it happens in the asking person object.
     *
     * @param askedPerson the person I want to add
     */
    @JsonIgnore
    @Override
    public boolean addFriend(final IAccount askedPerson) {
        if (this.getFriendList().contains(askedPerson.getAccount()) && askedPerson.getFriendList().contains(this.getAccount())) {
            return true;
        }

        // Other person already sent request
        if (askedPerson.getSentRequests().contains(this.getId())) {
            askedPerson.getSentRequests().remove(this.getId());
            this.receivedRequests.remove(askedPerson.getId());
            askedPerson.getFriendList().add(this.getId());

            this.friendList.add(askedPerson.getId());
            return true;
        } else { // Other person did not already send a request
            if (!askedPerson.getReceivedRequests().contains(this.getId()))
                askedPerson.getReceivedRequests().add(this.getId());

            if (!this.sentRequests.contains(askedPerson.getId()))
                this.sentRequests.add(askedPerson.getId());
            return false;
        }
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void aboutRequest(IAccount askedPerson) {
        this.friendList.remove(askedPerson.getId());
        this.sentRequests.remove(askedPerson.getId());
        this.receivedRequests.remove(askedPerson.getId());

        askedPerson.getFriendList().remove(this.getId());
        askedPerson.getSentRequests().remove(this.getId());
        askedPerson.getReceivedRequests().remove(this.getId());
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

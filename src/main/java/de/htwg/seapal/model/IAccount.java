package de.htwg.seapal.model;

import java.util.List;

public interface IAccount extends IModel {
    List<String>getFriendList();

    void setFriendList(List<String> friendList);

    String getPassword();

    void setPassword(String password);

    void setResetToken(String token);

    String getResetToken();

    void setResetTimeout(long timeout);

    long getResetTimeout();

    List<String> getSentRequests();

    void getRequestFromAccount(List<String> setList);

    List<String> getReceivedRequests();

    void getRequestToAccount(List<String> setList);

    String getGoogleID();

    void setGoogleID(String openID);

    boolean addFriend(final IAccount askedPerson);

    String getEmail();

    void setEmail(String email);

    void abortRequest(IAccount askedPerson);
}

package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IAccount;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * this document is used to exctract the information a user is able to see from an account object. Every account has
 * its account object, containing all the data the user isn't able to change directly (see doc in Account.java). The
 * class PublicPerson represents the JSON object to be sent to the user, such as friend lists and email adress.
 */
public final class PublicPerson {
    private String email;
    private List<String> friend_list = new LinkedList<>();
    private List<String> sentRequests = new LinkedList<>();
    private List<String> receivedRequests = new LinkedList<>();
    private String id;

    public PublicPerson(IAccount person) {
        if (person != null) {
            email = person.getEmail();
            friend_list = person.getFriendList();
            sentRequests = person.getSentRequests();
            receivedRequests = person.getReceivedRequests();
            id = person.getId();
        }
    }

    @JsonProperty("_id")
    public String getId() {
        return this.id;
    }

    public List<String> getFriend_list() {
        return friend_list;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getSentRequests() {
        return sentRequests;
    }

    public List<String> getReceivedRequests() {
        return receivedRequests;
    }
}

package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IAccount;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public final class PublicPerson {
    private String email;
    private List<String> friendList = new LinkedList<>();
    private List<String> sentRequests = new LinkedList<>();
    private List<String> receivedRequests = new LinkedList<>();
    private String id;

    public PublicPerson(IAccount person) {
        if (person != null) {
            email = person.getEmail();
            friendList = person.getFriendList();
            sentRequests = person.getSentRequests();
            receivedRequests = person.getReceivedRequests();
            id = person.getId();
        }
    }

    @JsonProperty("_id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("friend_list")
    public List<String> getFriendList() {
        return friendList;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("sentRequests")
    public List<String> getSentRequests() {
        return sentRequests;
    }

    @JsonProperty("receivedRequests")
    public List<String> getReceivedRequests() {
        return receivedRequests;
    }
}

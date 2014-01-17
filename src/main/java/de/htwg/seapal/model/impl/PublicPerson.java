package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IPerson;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public final class PublicPerson {
    private final String firstname;
    private final String id;
    private final String lastname;
    private final Long birth;
    private final Long registration;
    private final String nationality;
    private final String email;
    private final String city;
    private final String telephone;
    private final String mobile;
    private final String street;
    private final Integer postcode;
    private final List<String> friendList;
    private final List<String> sentRequests;
    private final List<String> receivedRequests;

    public PublicPerson(IPerson person) {
        this.firstname = person.getFirstname();
        this.id = person.getId();
        this.lastname = person.getLastname();
        this.birth = person.getBirth();
        this.registration = person.getRegistration();
        this.nationality = person.getNationality();
        this.email = person.getEmail();
        this.city = person.getCity();
        this.telephone = person.getTelephone();
        this.mobile = person.getMobile();
        this.street = person.getStreet();
        this.postcode = person.getPostcode();
        this.friendList = person.getFriendList();
        this.sentRequests = person.getSentRequests();
        this.receivedRequests = person.getReceivedRequests();
    }

    @JsonProperty("first_name")
    public String getFirstname() {
        return this.firstname;
    }

    @JsonProperty("last_name")
    public String getLastname() {
        return this.lastname;
    }

    @JsonProperty("birth")
    public Long getBirth() {
        return this.birth;
    }

    @JsonProperty("registration")
    public Long getRegistration() {
        return this.registration;
    }

    @JsonProperty("nationality")
    public String getNationality() {
        return this.nationality;
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    @JsonProperty("telephone")
    public String getTelephone() {
        return this.telephone;
    }

    @JsonProperty("mobile")
    public String getMobile() {
        return this.mobile;
    }

    @JsonProperty("street")
    public String getStreet() {
        return this.street;
    }

    @JsonProperty("postcode")
    public Integer getPostcode() {
        return this.postcode;
    }

    @JsonProperty("city")
    public String getCity() {
        return this.city;
    }

    @JsonProperty("country")
    public String getCountry() {
        return this.city;
    }

    @JsonProperty("friend_list")
    public List<String> getFriendList() {
        return this.friendList;
    }

    @JsonProperty("sent_requests")
    public List<String> getSentRequests() {
        return this.sentRequests;
    }

    @JsonProperty("received_requests")
    public List<String> getReceivedRequests() {
        return this.receivedRequests;
    }

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }
}

package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.ModelDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person
        extends ModelDocument
        implements IPerson {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String firstname = null;

    private String lastname = null;

    private Long birth;

    private Long registration;

    private Integer age = 0;

    private String nationality = null;

    private String email = null;

    private String telephone = null;

    private String mobile = null;

    private String street = null;

    private Integer postcode = 0;

    private String city = null;

    private String country = null;

    private String password = null;

    private String token;

    private long timeout;

    private List<String> friendList = new ArrayList<>();

    private List<String> sentRequests = new ArrayList<>();

    private List<String> receivedRequests = new ArrayList<>();

    public Person() {
        super(UUID.randomUUID().toString());
        firstname = "";
        lastname = "";
        birth = 0L;
        registration = 0L;
        age = 0;
        nationality = "";

        email = "";
        telephone = "";
        mobile = "";

        street = "";
        postcode = 0;
        city = "";
        country = "";
    }

    public Person(IPerson person) {
        super(person.getId());

        firstname = person.getFirstname();
        lastname = person.getLastname();
        birth = person.getBirth();
        registration = person.getRegistration();
        age = person.getAge();
        nationality = person.getNationality();

        email = person.getEmail();
        telephone = person.getTelephone();
        mobile = person.getMobile();

        street = person.getStreet();
        postcode = person.getPostcode();
        city = person.getCity();
        country = person.getCountry();
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public Long getBirth() {
        return birth;
    }

    @Override
    public void setBirth(Long birth) {
        this.birth = birth;
    }

    @Override
    public Long getRegistration() {
        return registration;
    }

    @Override
    public void setRegistration(Long registration) {
        this.registration = registration;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public void setNationality(String nationality) {
        this.nationality = nationality;
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
    public String getTelephone() {
        return telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public Integer getPostcode() {
        return postcode;
    }

    @Override
    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
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
    @JsonIgnore
    public void addFriend(final String uuid) {
        friendList.add(uuid);
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

    /**
     * the instance calling addFriend wants to add askedPerson to his friendList.
     * friendList contains approved friends
     * sentRequests contains uuids I have sent requests to
     * receivedRequests contains uuids I have received requests from
     *
     * @param askedPerson the person I want to add
     */
    @JsonIgnore
    @Override
    public boolean addFriend(final IPerson askedPerson) {
        // Other person already sent request
        if (askedPerson.getSentRequests().contains(this.getId())) {
            askedPerson.getSentRequests().remove(this.getId());
            askedPerson.getFriendList().add(this.getId());

            this.friendList.add(askedPerson.getId());
            return true;
        } else { // Other person did not already send a request
            askedPerson.getReceivedRequests().add(this.getId());
            this.sentRequests.add(askedPerson.getId());
            return false;
        }
    }
}

package de.htwg.seapal.model;

import java.util.List;

public interface IPerson extends IModel {

	String getFirstname();

	void setFirstname(String firstname);

	String getLastname();

	void setLastname(String lastname);

	Long getBirth();

	void setBirth(Long birth);

	Long getRegistration();

	void setRegistration(Long registration);

	Integer getAge();

	void setAge(Integer age);

	String getNationality();

	void setNationality(String nationality);

	String getEmail();

	void setEmail(String email);

	String getTelephone();

	void setTelephone(String telephone);

	String getMobile();

	void setMobile(String mobile);

	String getStreet();

	void setStreet(String street);

	Integer getPostcode();

	void setPostcode(Integer postcode);

	String getCity();

	void setCity(String city);

	String getCountry();

	void setCountry(String country);

    List<String> getFriendList();

    void setFriendList(List<String> friendList);

    String getPassword();

    void setPassword(String password);

    void setResetToken(String token);

    String getResetToken();

    void setResetTimeout(long timeout);

    long getResetTimeout();
}

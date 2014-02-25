package de.htwg.seapal.model;

public interface IWaypoint extends IModel {
	String getName();

	String getNote();

	Integer getBTM();

	Integer getDTM();

	Integer getCOG();

	Integer getSOG();

	String getHeadedFor();

    String getManeuver();

    String getForesail();

    String getMainsail();

	void setForesail(String foreSail);

	void setName(String name);

	void setNote(String note);

	void setBTM(Integer btm);

	void setDTM(Integer dtm);

	void setCOG(Integer cog);

	void setSOG(Integer sog);

	void setHeadedFor(String markId);

	void setManeuver(String maneuver);

	void setMainsail(String mainSail);

	String getTrip();

	void setTrip(String tripId);

	Long getDate();

	void setDate(Long date);

	Double getLat();

	void setLat(Double latitude);

	Double getLng();

	void setLng(Double Longitude);

    void setBoat(String s);

    String getBoat();

    String getImage_thumb();

    void setImage_thumb(String image);
}

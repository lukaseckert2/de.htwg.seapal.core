package de.htwg.seapal.model;

public interface IWaypoint extends IModel {
	String getName();

	String getNote();

    String getBTM();

    String getDTM();

    String getCOG();

    String getSOG();

	String getHeadedFor();

    String getManeuver();

    String getForesail();

    String getMainsail();

	void setForesail(String foreSail);

	void setName(String name);

	void setNote(String note);

	void setBTM(String btm);

	void setDTM(String dtm);

	void setCOG(String cog);

	void setSOG(String sog);

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

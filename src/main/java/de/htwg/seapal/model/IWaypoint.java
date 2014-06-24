package de.htwg.seapal.model;

public interface IWaypoint extends IModel {
	String getName();

	String getNote();

    String getBtm();

    String getDtm();

    String getCog();

    String getSOG();

	String getHeadedFor();

    String getManeuver();

    String getForesail();

    String getMainsail();
    
    
    

	void setForesail(String foreSail);

	void setName(String name);

	void setNote(String note);

	void setBtm(String btm);

	void setDtm(String dtm);

	void setCog(String cog);

	void setSog(String sog);

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
    
    Double getTempCelsius();
    void setTempCelcius(Double degrees);
    
    Double getWindSpeedBeaufort();
    void setWindSpeedBeaufort(Double beaufort);
    
    Double getWindDirection();
    void setWindDirection(Double degrees);
    
    Double getWavesHeight();
    void setWavesHeight(Double meters);
    
    Double getCloudage();
    void setCloudage(double value);
    
    Double getAtmosPressure();
    void setAtmosPressure(double hectopascal);
    
    Double getHumidity();
    void setHumidity(double percent);
    
}

package de.htwg.seapal.model;

public interface IWaypoint extends IModel {



    public enum Maneuver {
		NONE, TACK, JIBE, LAYTO, SET_SAILS, CHANGE_SAILS, SAILS_DOWN, REFF, ANKER_UP, ANKER_DOWN;
    }


	public enum ForeSail {
		NONE, GENUA1, GENUA2, GENUA3, FOCK, STORM_FOCK, BISTER, SPINACKER;
    }


	public enum MainSail {
		NONE, FULL, REEF1, REEF2;
    }
	String getName();

	String getNote();

	Integer getBTM();

	Integer getDTM();

	Integer getCOG();

	Integer getSOG();

	/**
	 * Gets the mark UUID of the target.
	 * @return The mark UUID as String.
	 */
	String getHeadedFor();

	Maneuver getManeuver();

	ForeSail getForesail();

	MainSail getMainsail();

	void setForesail(ForeSail foreSail);

	void setName(String name);

	void setNote(String note);

	void setBTM(Integer btm);

	void setDTM(Integer dtm);

	void setCOG(Integer cog);

	void setSOG(Integer sog);

	void setHeadedFor(String markId);

	void setManeuver(Maneuver maneuver);

	void setMainsail(MainSail mainSail);

	String getTrip();

	void setTrip(String tripId);

	Long getDate();

	void setDate(Long date);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double Longitude);

    void setBoat(String s);

    String getBoat();
}

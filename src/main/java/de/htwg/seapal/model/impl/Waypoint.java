package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IWaypoint;
import de.htwg.seapal.model.ModelDocument;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.UUID;

public class Waypoint extends ModelDocument implements IWaypoint {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private Double lat;
    private Double lng;
    private Long date;
    private String note;
    private String btm;
    private String dtm;
    private String cog;
    private String sog;
    private String headedFor;
    private String maneuver;
    private String foreSail;
    private String mainSail;
    private String trip;
    private String boat;
    private String image_thumb;
    private Double tempCelsius;
    private Double windSpeed;
    private Double windDirection;
    private Double wavesHeight;
    private Double atmosPressure;
    private Double cloudage;

    public Waypoint() {
        super(UUID.randomUUID().toString());
        maneuver = "";
        foreSail = "";
        mainSail = "";
    }

    public Waypoint(IWaypoint w) {
        super(w);
        this.name = w.getName();
        this.lat = w.getLat();
        this.lng = w.getLng();
        this.date = w.getDate();
        this.note = w.getNote();
        this.btm = w.getBtm();
        this.dtm = w.getDtm();
        this.cog = w.getCog();
        this.sog = w.getSOG();
        this.headedFor = w.getHeadedFor();
        this.maneuver = w.getManeuver();
        this.foreSail = w.getForesail();
        this.mainSail = w.getMainsail();
        this.trip = w.getTrip();
        this.tempCelsius = w.getTempCelsius();
        this.windSpeed = w.getWindSpeedBeaufort();
        this.windDirection = w.getWindDirection();
        this.wavesHeight = w.getWavesHeight();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public String getBtm() {
        return btm;
    }

    @Override
    public String getDtm() {
        return dtm;
    }

    @Override
    public String getCog() {
        return cog;
    }

    @Override
    public String getSOG() {
        return sog;
    }

    @Override
    public String getHeadedFor() {
        if (headedFor == "") {
            return null;
        }
        return headedFor;
    }

    @Override
    public String getManeuver() {
        return maneuver;
    }

    @Override
    public String getForesail() {
        return foreSail;
    }

    @Override
    public String getMainsail() {
        return mainSail;
    }

    @Override
    public void setForesail(final String foreSail) {
        this.foreSail = foreSail;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public void setBtm(final String btm) {
        this.btm = btm;
    }

    @Override
    public void setDtm(final String dtm) {
        this.dtm = dtm;
    }

    @Override
    public void setCog(final String Cog) {
        this.cog = Cog;
    }

    @Override
    public void setSog(final String sog) {
        this.sog = sog;
    }

    @Override
    public void setHeadedFor(final String headedFor) {
        this.headedFor = headedFor;
    }

    @Override
    public void setManeuver(final String maneuver) {
        this.maneuver = maneuver;
    }

    @Override
    public void setMainsail(final String mainSail) {
        this.mainSail = mainSail;
    }

    @Override
    public String getTrip() {
        if (trip == "") {
            return null;
        }
        return trip;
    }


    @Override
    public void setTrip(String tripId) {
        this.trip = tripId;
    }

    @Override
    public Long getDate() {
        return date;
    }

    @Override
    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public Double getLat() {
        return lat;
    }

    @Override
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public Double getLng() {
        return lng;
    }

    @Override
    public void setLng(Double Longitude) {
        this.lng = Longitude;
    }

    @Override
    public void setBoat(final String s) {
        this.boat = s;
    }

    @Override
    public String getBoat() {
        return this.boat;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String getImage_thumb() {
        return this.image_thumb;
    }

    @Override
    public void setImage_thumb(final String image) {
        this.image_thumb = image;
    }

    @Override
    public boolean isValid() {
        return boat != null && !boat.equals("") && trip != null && !trip.equals("");
    }

	@Override
	public Double getTempCelsius() {
		return this.tempCelsius;
	}

	@Override
	public void setTempCelcius(Double degrees) {
		this.tempCelsius = degrees;
	}

	@Override
	public Double getWindSpeedBeaufort() {
		return this.windSpeed;
	}

	@Override
	public void setWindSpeedBeaufort(Double beaufort) {
		this.windSpeed = beaufort;
	}

	@Override
	public Double getWindDirection() {
		return this.windDirection;
	}

	@Override
	public void setWindDirection(Double degrees) {
		this.windDirection = degrees;
	}

	@Override
	public Double getWavesHeight() {
		return this.wavesHeight;
	}

	@Override
	public void setWavesHeight(Double meters) {
		this.wavesHeight = meters;
	}

	@Override
	public Double getCloudage() {
		return this.cloudage;
	}

	@Override
	public void setCloudage(double value) {
		this.cloudage = value;
	}

	@Override
	public Double getAtmosPressure() {
		return this.atmosPressure;
	}

	@Override
	public void setAtmosPressure(double hectopascal) {
		this.atmosPressure = hectopascal;
	}
}

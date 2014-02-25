package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IWaypoint;
import de.htwg.seapal.model.ModelDocument;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @JsonIgnore
    public Object _attachments;

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
        this.btm = w.getBTM();
        this.dtm = w.getDTM();
        this.cog = w.getCOG();
        this.sog = w.getSOG();
        this.headedFor = w.getHeadedFor();
        this.maneuver = w.getManeuver();
        this.foreSail = w.getForesail();
        this.mainSail = w.getMainsail();
        this.trip = w.getTrip();
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
    public String getBTM() {
        return btm;
    }

    @Override
    public String getDTM() {
        return dtm;
    }

    @Override
    public String getCOG() {
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
    public void setBTM(final String btm) {
        this.btm = btm;
    }

    @Override
    public void setDTM(final String dtm) {
        this.dtm = dtm;
    }

    @Override
    public void setCOG(final String cog) {
        this.cog = cog;
    }

    @Override
    public void setSOG(final String sog) {
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
}

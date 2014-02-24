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
	private Long date; // unix timestamp
	private String note;
	private Integer btm;
	private Integer dtm;
	private Integer cog;
	private Integer sog;
	private String headedFor; // UUID Mark
	private Maneuver maneuver;
	private ForeSail foreSail;
	private MainSail mainSail;
	private String trip; // UUID Trip
    private String boat;
    private String image_thumb;
    @JsonIgnore
    public Object _attachments;

    public Waypoint() {
		super(UUID.randomUUID().toString());
		maneuver = Maneuver.NONE;
		foreSail = ForeSail.NONE;
		mainSail = MainSail.NONE;
	}

    /*
	@JsonCreator
	public Waypoint(@JsonProperty("maneuver") Maneuver m,
			@JsonProperty("foresail") ForeSail f,
			@JsonProperty("mainsail") MainSail msail) {
		super(UUID.randomUUID().toString());
		maneuver = m;
		foreSail = f;
		mainSail = msail;
		this.name = "";
		this.lat = 0D;
		this.lng = 0D;
		this.date = 0L;
		this.note = "";
		this.btm = 0;
		this.dtm = 0;
		this.cog = 0;
		this.sog = 0;
		this.headedFor = "";
		this.maneuver = m;
		this.foreSail = f;
		this.mainSail = msail;
		this.trip = "";
	}
    */

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
	public Integer getBTM() {
		return btm;
	}

	@Override
	public Integer getDTM() {
		return dtm;
	}

	@Override
	public Integer getCOG() {
		return cog;
	}

	@Override
	public Integer getSOG() {
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
	public Maneuver getManeuver() {
		return maneuver;
	}

	@Override
	public ForeSail getForesail() {
		return foreSail;
	}

	@Override
	public MainSail getMainsail() {
		return mainSail;
	}

	@Override
	public void setForesail(final ForeSail foreSail) {
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
	public void setBTM(final Integer btm) {
		this.btm = btm;
	}

	@Override
	public void setDTM(final Integer dtm) {
		this.dtm = dtm;
	}

	@Override
	public void setCOG(final Integer cog) {
		this.cog = cog;
	}

	@Override
	public void setSOG(final Integer sog) {
		this.sog = sog;
	}

	@Override
	public void setHeadedFor(final String headedFor) {
		this.headedFor = headedFor;
	}

	@Override
	public void setManeuver(final Maneuver maneuver) {
		this.maneuver = maneuver;
	}

	@Override
	public void setMainsail(final MainSail mainSail) {
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
}

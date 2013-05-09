package de.htwg.seapal.model.impl;

import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

import de.htwg.seapal.model.IWaypoint;

public class Waypoint extends CouchDbDocument implements IWaypoint {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double latitude;
	private double longitude;
	private long date; // unix timestamp
	private String note;
	private int btm;
	private int dtm;
	private int cog;
	private int sog;
	private String headedFor; // UUID Mark
	private Maneuver maneuver;
	private ForeSail foreSail;
	private MainSail mainSail;
	private String trip; // UUID Trip

	public Waypoint() {
		setId(UUID.randomUUID().toString());
		maneuver = Maneuver.NONE;
		foreSail = ForeSail.NONE;
		mainSail = MainSail.NONE;
	}
	
	@JsonCreator
	public Waypoint(@JsonProperty("maneuver") Maneuver m, 
			@JsonProperty("foresail") ForeSail f,
			@JsonProperty("mainsail") MainSail msail) 
	{
		setId(UUID.randomUUID().toString());
		maneuver = m;
		foreSail = f;
		mainSail = msail;
	}
	
	public Waypoint(IWaypoint w) 
	{
		setId(w.getId());
		this.name = w.getName();
		this.latitude = w.getLatitude();
		this.longitude = w.getLongitude();
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
	public UUID getUUID() {
		return UUID.fromString(getId());
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
	public int getBTM() {
		return btm;
	}

	@Override
	public int getDTM() {
		return dtm;
	}

	@Override
	public int getCOG() {
		return cog;
	}

	@Override
	public int getSOG() {
		return sog;
	}

	@Override
	public String getHeadedFor() {
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
	public void setBTM(final int btm) {
		this.btm = btm;
	}

	@Override
	public void setDTM(final int dtm) {
		this.dtm = dtm;
	}

	@Override
	public void setCOG(final int cog) {
		this.cog = cog;
	}

	@Override
	public void setSOG(final int sog) {
		this.sog = sog;
	}

	@Override
	public void setHeadedFor(final UUID headedFor) {
		this.headedFor = headedFor.toString();
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
	public String toString() {
		return "Waypoint [id=" + getId() + ", name=" + name + ", latitude="
				+ latitude + ", longitude=" + longitude + ", date=" + date
				+ ", note=" + note + ", btm=" + btm + ", dtm=" + dtm + ", cog="
				+ cog + ", sog=" + sog + ", headedFor=" + headedFor
				+ ", maneuver=" + maneuver + ", foreSail=" + foreSail
				+ ", mainSail=" + mainSail + ", trip=" + trip + "]";
	}

	@Override
	public String getTrip() {
		return trip;
	}

	@Override
	public void setTrip(String trip) {
		this.trip = trip.toString();
	}

	@Override
	public long getDate() {
		return date;
	}

	@Override
	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public double getLatitude() {
		return latitude;
	}

	@Override
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public double getLongitude() {
		return longitude;
	}

	@Override
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
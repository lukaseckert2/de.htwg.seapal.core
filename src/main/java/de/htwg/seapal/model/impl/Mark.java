package de.htwg.seapal.model.impl;

import java.util.UUID;

import org.ektorp.support.CouchDbDocument;

import de.htwg.seapal.model.IMark;

public class Mark extends CouchDbDocument implements IMark {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	private String user; // UUID user

	private String name;
	private double latitude;
	private double longitude;
	private int btm;
	private int dtm;
	private int cog;
	private int sog;
	private String note;
	private long date;
	private boolean isRouteMark;

	public Mark() {
		setId(UUID.randomUUID().toString());
	}
	
	public Mark(IMark m) {
		setId(m.getId());
		this.user = m.getUser();

		this.name = m.getName();
		this.latitude = m.getLatitude();
		this.longitude = m.getLongitude();
		this.btm = m.getBTM();
		this.dtm = m.getDTM();
		this.cog = m.getCOG();
		this.sog = m.getSOG();
		this.note = m.getNote();
		this.date = m.getDate();
		this.isRouteMark = m.isRouteMark();
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
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int getBTM() {
		return btm;
	}

	@Override
	public void setBTM(int btm) {
		this.btm = btm;
	}

	@Override
	public int getDTM() {
		return dtm;
	}

	@Override
	public void setDTM(int dtm) {
		this.dtm = dtm;
	}

	@Override
	public int getCOG() {
		return cog;
	}

	@Override
	public void setCOG(int cog) {
		this.cog = cog;
	}

	@Override
	public int getSOG() {
		return sog;
	}

	@Override
	public void setSOG(int sog) {
		this.sog = sog;
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
	public boolean isRouteMark() {
		return isRouteMark;
	}

	@Override
	public void setIsRouteMark(boolean isRouteMark) {
		this.isRouteMark = isRouteMark;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}
}

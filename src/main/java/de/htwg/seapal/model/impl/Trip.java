package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.ITrip;
import de.htwg.seapal.model.ModelDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trip extends ModelDocument implements ITrip {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = -2927732338890052773L;

    private String name;
    private String boat;
    private Long startDate;
    private Long endDate;
    private String from;
    private String to;
    private String skipper;
    private String duration;
    private String crew;
    private String notes;
    private List<Double> marks;

    public Trip() {
		super(UUID.randomUUID().toString());
        this.name = "";
        this.startDate = 0L;
        this.endDate = 0L;
        this.from = "";
        this.to = "";
        this.skipper = "";
        this.duration = "";
        this.crew = "";
        this.notes = "";
        this.marks = new ArrayList<>();
    }

	public Trip(ITrip t) {
        super(t);
        this.name = t.getName();
        this.startDate = t.getStartDate();
        this.endDate = t.getEndDate();
        this.from = t.getFrom();
        this.to = t.getTo();
        this.skipper = t.getSkipper();
        this.duration = t.getDuration();
        this.crew = t.getCrew();
        this.notes = t.getNotes();
        this.marks = t.getMarks();
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
    public Long getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    @Override
    public Long getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getSkipper() {
        return skipper;
    }

    @Override
    public void setSkipper(String skipper) {
        this.skipper = skipper;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String getCrew() {
        return crew;
    }

    @Override
    public void setCrew(String crew) {
        this.crew = crew;
    }

    @Override
    public List<Double> getMarks() {
        return this.marks;
    }

    @Override
    public void setMarks(List<Double> marks) {
        this.marks = marks;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBoat() {
        return boat;
    }

    public void setBoat(String boat) {
        this.boat = boat;
    }
}

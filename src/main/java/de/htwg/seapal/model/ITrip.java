package de.htwg.seapal.model;


import java.util.List;

public interface ITrip extends IModel {

	String getName();

	void setName(String name);

	String getNotes();

	void setNotes(String text);

	String getDuration();

    void setDuration(String duration);

    Long getStartDate();

    void setStartDate(Long startDate);

    Long getEndDate();

    void setEndDate(Long endDate);

    String getFrom();

    void setFrom(String from);

    String getTo();

    void setTo(String to);

    String getSkipper();

    void setSkipper(String skipper);

    String getCrew();

    void setCrew(String crew);

    List<Double> getMarks();

    void setMarks(List<Double> marks);

    public String getBoat();

    public void setBoat(String boat);
}

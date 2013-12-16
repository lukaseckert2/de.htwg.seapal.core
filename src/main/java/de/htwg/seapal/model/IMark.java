package de.htwg.seapal.model;

import org.codehaus.jackson.annotate.JsonProperty;

public interface IMark extends IModel {

	String getName();

	void setName(String name);

	Double getLatitude();

	void setLatitude(Double latitute);

	Double getLongitude();

	void setLongitude(Double Longitude);

	String getNote();

	void setNote(String note);

	Integer getBTM();

	void setBTM(Integer btm);

	Integer getDTM();

	void setDTM(Integer dtm);

	Integer getCOG();

	void setCOG(Integer cog);

	Integer getSOG();

	void setSOG(Integer sog);

	Long getDate();

	void setDate(Long date);

	@JsonProperty("is_route")
	Boolean getIsRouteMark();

	@JsonProperty("is_route")
	void setIsRouteMark(Boolean isRouteMark);

}

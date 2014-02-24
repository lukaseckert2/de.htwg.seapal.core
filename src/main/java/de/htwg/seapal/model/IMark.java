package de.htwg.seapal.model;

public interface IMark extends IModel {

	String getName();

	void setName(String name);

    Double getLat();

	void setLat(Double latitute);

    Double getLng();

	void setLng(Double Longitude);

	Long getDate();

	void setDate(Long date);

    String getImage_thumb();

    void setImage_thumb(String image);
}

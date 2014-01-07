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

	Long getDate();

	void setDate(Long date);

    @JsonProperty("image_big")
    String getPhoto();

    @JsonProperty("image_big")
    void setPhoto(String image);

    @JsonProperty("image_thumb")
    String getThumbnail();

    @JsonProperty("image_thumb")
    void setThumbnail(String image);
}

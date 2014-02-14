package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IMark;
import de.htwg.seapal.model.ModelDocument;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.UUID;

public class Mark extends ModelDocument implements IMark {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Double latitude;
	private Double Longitude;
	private Long date;
    private String thumbnail;
    @JsonIgnore
    public Object _attachments;

    public Mark() {
		super(UUID.randomUUID().toString());
		this.latitude = 0D;
		this.Longitude = 0D;
		this.date = 0L;
        this.thumbnail = "";
	}

	public Mark(IMark m) {
        super(m);

		this.name = m.getName();
		this.latitude = m.getLatitude();
		this.Longitude = m.getLongitude();
		this.date = m.getDate();
        this.thumbnail = m.getThumbnail();
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
    @JsonProperty("lat")
    public Double getLatitude() {
		return this.latitude;
	}

	@Override
    @JsonProperty("lat")
    public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
    @JsonProperty("lng")
	public Double getLongitude() {
		return this.Longitude;
	}

	@Override
    @JsonProperty("lng")
    public void setLongitude(Double Longitude) {
		this.Longitude = Longitude;
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
    public String getThumbnail() {
        return this.thumbnail;
    }
    @Override
    public void setThumbnail(final String image) {
        this.thumbnail = image;
    }
}

package de.htwg.seapal.model.impl;

import de.htwg.seapal.model.IMark;
import de.htwg.seapal.model.ModelDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.UUID;

public class Mark extends ModelDocument implements IMark {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Double lat;
	private Double lng;
	private Long date;
    private String image_thumb;
    @JsonIgnore
    public Object _attachments;

    public Mark() {
		super(UUID.randomUUID().toString());
		this.lat = 0D;
		this.lng = 0D;
		this.date = 0L;
        this.image_thumb = "";
	}

	public Mark(IMark m) {
        super(m);

		this.name = m.getName();
		this.lat = m.getLat();
		this.lng = m.getLng();
		this.date = m.getDate();
        this.image_thumb = m.getImage_thumb();
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
    public Double getLat() {
		return this.lat;
	}

	@Override
    public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public Double getLng() {
		return this.lng;
	}

	@Override
    public void setLng(Double Longitude) {
		this.lng = Longitude;
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
    public String getImage_thumb() {
        return this.image_thumb;
    }
    @Override
    public void setImage_thumb(final String image) {
        this.image_thumb = image;
    }
}

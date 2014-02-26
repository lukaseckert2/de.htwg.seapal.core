package de.htwg.seapal.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * Alternative base class to CouchDbDocument, which allows to set an ID twice.
 * This is neccessary to override the default ID with the UUID of seapal. Also
 * this implementation does not implements attachments, because this CouchDB
 * feature is not required.
 *
 * @author Benjamin
 */
public abstract class ModelDocument implements IModel, Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String revision;
    private String account;
    private Map<String, Attachement> _attachments;


    public ModelDocument(final String uuid) {
        this.id = uuid;
    }

    public ModelDocument(IModel model) {
        this.id = model.getId();
        this.revision = model.getRevision();
        this.account = model.getAccount();
    }

    @JsonIgnore
	@Override
	public UUID getUUID() {
		return UUID.fromString(id);
	}

	@JsonProperty("_id")
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Sets the UUID.
	 *
	 * @param uuid
	 *            The UUID.
	 */
	@JsonProperty("_id")
	public void setId(String uuid) {
		//if (uuid == null || uuid.equals(""))
		//	return; // TODO find out what this code was supposed to do
		id = uuid;
	}

	/**
	 * Gets the revision.
	 *
	 * @return The revision.
	 */
	@JsonProperty("_rev")
	public String getRevision() {
		return revision;
	}

	/**
	 * Sets the revision.
	 *
	 * @param rev
	 *            The revision.
	 */
	@JsonProperty("_rev")
	public void setRevision(String rev) {
		this.revision = rev;
	}

	/**
	 * Indicates whether the data entry is new.
	 *
	 * @return TRUE, if there is no revision and the data is newly created.
	 */
	@JsonIgnore
	public boolean isNew() {
		return revision == null || revision.equals("");
	}

	/**
	 * Gets the revision.
	 * <p>
	 * Note: This duplicated method is necessary for Forms.bindFromRequest() of
	 * Play!.
	 * </p>
	 *
	 * @return The revision.
	 */
	@JsonIgnore
	public String get_rev() {
		return revision;
	}

	/**
	 * Sets the revision.
	 * <p>
	 * Note: This duplicated method is necessary for Forms.bindFromRequest() of
	 * Play!.
	 * </p>
	 *
	 * @param rev
	 *            The revision.
	 */
	@JsonIgnore
	public void set_rev(String rev) {
		this.revision = rev;
	}

	/**
	 * Gets the UUID.
	 * <p>
	 * Note: This duplicated method is necessary for Forms.bindFromRequest() of
	 * Play!.
	 * </p>
	 *
	 * @return The UUID.
	 */
	@JsonIgnore
	public String get_id() {
		return id;
	}

	/**
	 * Sets the UUID.
	 * <p>
	 * Note: This duplicated method is necessary for Forms.bindFromRequest() of
	 * Play!.
	 * </p>
	 *
	 * @param uuid
	 *            The UUID.
	 */
	@JsonIgnore
	public void set_id(String uuid) {
        //if (uuid == null || uuid.equals(""))
        //	return; // TODO find out what this code was supposed to do
        id = uuid;
	}

    @JsonProperty("owner")
    @Override
    public String getAccount() {
        return account;
    }

    @JsonProperty("owner")
    @Override
    public void setAccount(String uuid) {
        this.account = uuid;
    }

    @Override
    public Map<String, Attachement> get_attachments() {
        return _attachments;
    }

    @Override
    public void set_attachments(Map<String, Attachement> _attachments) {
        this._attachments = _attachments;
    }
}

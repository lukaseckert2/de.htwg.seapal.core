package de.htwg.seapal.model;

import java.io.Serializable;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import org.ektorp.util.*;

/**
 * Alternative base class to CouchDbDocument, which allows to set an ID twice.
 * This is neccessary to override the default ID with the UUID of seapal. Also this implementation
 * does not implements attachments, because this CouchDB feature is not required.
 * @author Benjamin
 */
public abstract class ModelDocument implements IModel, Serializable {
	private static final long serialVersionUID = 1L;
    private String id;
    private String revision;
    
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
     * @param id The UUID.
     */
    @JsonProperty("_id")
    public void setId(String uuid) {
            Assert.hasText(uuid, "id must have a value");
            id = uuid;
    }
    
    /**
     * Gets the revision.
     * @return The revision.
     */
    @JsonProperty("_rev")
    public String getRevision() {
            return revision;
    }
    
    /**
     * Sets the revision.
     * @param rev The revision.
     */
    @JsonProperty("_rev")
    public void setRevision(String rev) {
            this.revision = rev;
    }
    
    /**
     * Indicates whether the data entry is new.
     * @return TRUE, if there is no revision and the data is newly created.
     */
    @JsonIgnore
    public boolean isNew() {
            return revision == null;
    }
}

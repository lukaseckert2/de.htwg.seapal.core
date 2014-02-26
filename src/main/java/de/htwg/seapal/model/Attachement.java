package de.htwg.seapal.model;

/**
 * a simple document representing an attachement in couchdb. will be saved to a Map<String, Attachement>.
 *     "_attachments": {
 *        "photo": {
 *            "content_type": "image/jpeg",
 *            "revpos": 4,
 *            "digest": "md5-FG8DlMjSN+YOPSZKzxDb0A==",
 *            "length": 265777,
 *            "stub": true
 *        }
 *    }
 */
public final class Attachement {
    private String content_type;
    private Integer revpos;
    private String digest;
    private Integer length;
    private Boolean stub;

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public Integer getRevpos() {
        return revpos;
    }

    public void setRevpos(Integer revpos) {
        this.revpos = revpos;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getStub() {
        return stub;
    }


    public void setStub(Boolean stub) {
        this.stub = stub;
    }
}

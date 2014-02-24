package de.htwg.seapal.controller;

import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

public interface IMainController {

    Collection<? extends IModel> getSingleDocument(String document, String session, UUID id);

    boolean deleteDocument(String document, String session, UUID id);

    Collection<? extends IModel> getOwnDocuments(String document, final String session);

    Collection<? extends IModel> getByParent(String document, String parent, String session, final UUID id);

    public ModelDocument creatDocument(final String type, final ModelDocument document, String session);

    Collection<? extends IModel> account(final UUID account, String session);

    boolean addFriend(String session, UUID askedPersonUUID);

    Collection<? extends IModel> account(String session);

    Collection<? extends IModel> getDocuments(String document, String session, String userid, String scope);

    boolean addFriend(String session, String mail);

    void abortRequest(String session, UUID id);

    public boolean addPhoto(String session, UUID uuid, String contentType, File file, String type) throws FileNotFoundException;

    public InputStream getPhoto(String session, UUID uuid, String type) throws FileNotFoundException;
}

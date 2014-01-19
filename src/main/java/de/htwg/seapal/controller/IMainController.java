package de.htwg.seapal.controller;

import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;

import java.util.Collection;
import java.util.UUID;

public interface IMainController {

    Collection<? extends IModel> getSingleDocument(String session, UUID id, String document);

    boolean deleteDocument(String session, UUID id, String document);

    Collection<? extends IModel> getOwnDocuments(String document, final String session);

    Collection<? extends IModel> getForeignDocuments(String document, final String session);

    Collection<? extends IModel> getByParent(String document, String parent, String session, final UUID id);

    public ModelDocument creatDocument(final String type, final ModelDocument document, String session);

    Collection<? extends IModel> account(final UUID account, String session);

    boolean addFriend(String session, UUID askedPersonUUID);

    Collection<? extends IModel> account(String session);

    Collection<? extends IModel> getDocuments(String document, String session, String scope);

    boolean addFriend(String session, String mail);

    void abortRequest(String session, UUID id);
}

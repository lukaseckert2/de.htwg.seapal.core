package de.htwg.seapal.controller;

import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.PublicPerson;

import java.util.Collection;
import java.util.UUID;

public interface IMainController {

    Collection<? extends IModel> getSingleDocument(String session, UUID id, String document);

    boolean deleteDocument(String session, UUID id, String document);

    Collection<? extends IModel> getOwnDocuments(String document, final String session);

    Collection<? extends IModel> getForeignDocuments(String document, final String session);

    Collection<? extends IModel> getByParent(String document, String parent, String session, final UUID id);

    public ModelDocument creatDocument(final String type, final ModelDocument document);

    PublicPerson account(final UUID account, String session);

    boolean addFriend(String session, UUID askedPersonUUID);

    PublicPerson account(String session);

    Collection<? extends IModel> getDocuments(String document, String session, String scope);
}

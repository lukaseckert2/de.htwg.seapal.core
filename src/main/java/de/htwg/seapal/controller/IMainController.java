package de.htwg.seapal.controller;

import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.IPerson;
import de.htwg.seapal.model.ModelDocument;

import java.util.List;
import java.util.UUID;

public interface IMainController {

    List<? extends IModel> getSingleDocument(String session, UUID id, String document);

    boolean deleteDocument(String session, UUID id, String document);

    List<? extends IModel> getOwnDocuments(String document, final String session);

    List<? extends IModel> getForeignDocuments(String document, final String session);

    List<? extends IModel> getByParent(String document, String parent, String session, final UUID id);

    public ModelDocument creatDocument(final String type, final ModelDocument document);

    IPerson account(final UUID account, String session);

    boolean addFriend(String session, UUID askedPersonUUID);

    IPerson account(String session);
}

package de.htwg.seapal.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.model.IAccount;
import de.htwg.seapal.model.IModel;
import de.htwg.seapal.model.ModelDocument;
import de.htwg.seapal.model.impl.Account;
import de.htwg.seapal.model.impl.Boat;
import de.htwg.seapal.model.impl.SignupAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

public final class MainControllerTest {
    private Injector injector;
    private IMainController controller;
    private IAccountController accountController;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new TestModule());
        accountController = injector.getInstance(IAccountController.class);
        controller = injector.getInstance(IMainController.class);
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "setupDB.sh");
        pb.directory(new File("/Users/mogli/Code/Seapal-Web/setupDB/"));
        Process p = pb.start();
        p.waitFor();
    }

    @After
    public void tearDown() throws Exception {
        System.gc();
    }

    private Boat createBoat(UUID owner) {
        Boat boat = new Boat();
        boat.setAccount(String.valueOf(owner));
        return boat;
    }

    @Test
    public void testGetSingleDocument() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = createBoat(owner);
        ModelDocument boat2 = controller.creatDocument("boat", boat, owner.toString());
        Collection<? extends IModel> collection = controller.getSingleDocument("boat", owner.toString(), boat.getUUID());
        assert (collection != null);
        assert (collection.size() == 1);
        assert (boat.equals(collection.toArray()[0]));
    }

    @Test
    public void testDeleteDocument() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = createBoat(owner);
        controller.creatDocument("boat", boat, owner.toString());
        assert (controller.deleteDocument("boat", owner.toString(), boat.getUUID()));
        Collection<? extends IModel> collection = controller.getSingleDocument("boat", owner.toString(), boat.getUUID());
        assert (collection != null);
        assert (collection.size() == 0);
    }

    @Test
    public void testDeleteDocument2() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = createBoat(owner);
        controller.creatDocument("boat", boat, owner.toString());
        assert (!controller.deleteDocument("boat", owner.toString(), UUID.randomUUID()));
        Collection<? extends IModel> collection = controller.getSingleDocument("boat", owner.toString(), boat.getUUID());
        assert (collection != null);
        assert (collection.size() == 1);
    }

    @Test
    public void testGetOwnDocuments() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = createBoat(owner);
        controller.creatDocument("boat", boat, owner.toString());
        Boat boat2 = createBoat(owner);
        controller.creatDocument("boat", boat2, owner.toString());
        UUID owner2 = UUID.randomUUID();
        Boat boat3 = createBoat(owner2);
        controller.creatDocument("boat", boat3, owner.toString());
        Collection<? extends IModel> collection = controller.getOwnDocuments("boat", owner.toString());
        assert (collection != null);
        assert (collection.size() == 2);
        if (((Boat) collection.toArray()[0]).getId().equals(boat.getId())) {
            assert (((Boat) collection.toArray()[1]).getId().equals(boat2.getId()));
        } else if (((Boat) collection.toArray()[0]).getId().equals(boat2.getId())) {
            assert (((Boat) collection.toArray()[1]).getId().equals(boat.getId()));
        } else {
            assert (false);
        }
    }

    @Test
    public void testGetByParent() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = createBoat(owner);
        ModelDocument boat2 = controller.creatDocument("boat", boat, owner.toString());
        Collection<? extends IModel> collection = controller.getByParent("boat", "singleDocument", owner.toString(), boat.getUUID());
        assert (collection != null);
        assert (collection.size() == 1);
        assert (boat.equals(collection.toArray()[0]));
    }

    @Test
    public void testCreatDocument() throws Exception {
        UUID owner = UUID.randomUUID();
        Boat boat = new Boat();
        ModelDocument boat2 = controller.creatDocument("boat", boat, owner.toString());
        controller.creatDocument("boat", boat, owner.toString());
        Collection<? extends IModel> collection = controller.getSingleDocument("boat", owner.toString(), boat.getUUID());
        assert (collection != null);
        assert (collection.size() == 1);
        assert (boat.equals(collection.toArray()[0]));
    }

    @Test
    public void testAccount() throws Exception {
        Account crewMember1 = new Account();
        crewMember1.setAccount(crewMember1.getUUID().toString());
        crewMember1.setEmail("crewMember1@123.de");
        crewMember1.setPassword("test");
        SignupAccount save = new SignupAccount(crewMember1, "Alfred", "von Tirpitz");
        accountController.saveAccount(save, true);

        IAccount crewMember2 = new Account();
        crewMember2.setAccount(crewMember2.getUUID().toString());
        crewMember2.setEmail("crewMember2@123.de");
        crewMember2.setPassword("test");
        SignupAccount save2 = new SignupAccount(crewMember2, "Ernst", "Lindemann");
        accountController.saveAccount(save2, true);

        IAccount account = new Account();
        account.setAccount(account.getUUID().toString());
        account.addFriend(crewMember1);
        crewMember1.addFriend(account);
        account.setEmail("account@123.de");
        account.setPassword("test");
        SignupAccount save3 = new SignupAccount(account, "Karl", "Dönitz");
        accountController.saveAccount(save3, true);

        Collection<? extends IModel> collection = controller.account(account.getUUID(), crewMember1.getAccount().toString());
        assert (collection != null);
        assert (collection.size() == 1);

        collection = controller.account(account.getUUID(), crewMember2.getAccount().toString());
        assert (collection != null);
        assert (collection.size() == 0);
    }

    @Test
    public void testAddFriend() throws Exception {

    }

    @Test
    public void testAccount2() throws Exception {
        Account crewMember1 = new Account();
        crewMember1.setAccount(crewMember1.getUUID().toString());
        crewMember1.setEmail("crewMember1@123.de");
        crewMember1.setPassword("test");
        SignupAccount save = new SignupAccount(crewMember1, "Alfred", "von Tirpitz");
        accountController.saveAccount(save, true);

        IAccount account = new Account();
        account.setAccount(account.getUUID().toString());
        account.addFriend(crewMember1);
        crewMember1.addFriend(account);
        account.setEmail("account@123.de");
        account.setPassword("test");
        SignupAccount save3 = new SignupAccount(account, "Karl", "Dönitz");
        accountController.saveAccount(save3, true);

        Collection<? extends IModel> collection = controller.account(account.getAccount());
        assert (collection != null);
        assert (collection.size() == 1);
        //assert ("Karl".equals((Person) collection.toArray()[0]).getFirstname());
    }

    @Test
    public void testGetDocuments() throws Exception {

    }

    @Test
    public void testAddFriend2() throws Exception {

    }

    @Test
    public void testAbortRequest() throws Exception {

    }
}

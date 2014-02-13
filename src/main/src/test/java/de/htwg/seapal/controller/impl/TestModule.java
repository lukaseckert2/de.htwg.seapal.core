package de.htwg.seapal.controller.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.htwg.seapal.controller.IAccountController;
import de.htwg.seapal.controller.IMainController;
import de.htwg.seapal.controller.IPersonController;
import de.htwg.seapal.database.*;
import de.htwg.seapal.utils.logging.ILogger;

public final class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IBoatDatabase.class).to(de.htwg.seapal.database.mock.BoatDatabase.class);
        bind(IPersonDatabase.class).to(de.htwg.seapal.database.mock.PersonDatabase.class);
        bind(ITripDatabase.class).to(de.htwg.seapal.database.mock.TripDatabase.class);
        bind(IMarkDatabase.class).to(de.htwg.seapal.database.mock.MarkDatabase.class);
        bind(IWaypointDatabase.class).to(de.htwg.seapal.database.mock.WaypointDatabase.class);
        bind(IRouteDatabase.class).to(de.htwg.seapal.database.mock.RouteDatabase.class);
        bind(IRaceDatabase.class).to(de.htwg.seapal.database.mock.RaceDatabase.class);
        bind(IAccountDatabase.class).to(de.htwg.seapal.database.mock.AccountDatabase.class);

        bind(IMainController.class).to(MainController.class).in(Singleton.class);
        bind(IAccountController.class).to(AccountController.class).in(Singleton.class);
        bind(IPersonController.class).to(PersonController.class).in(Singleton.class);
        bind(ILogger.class).to(Logger.class);
    }

    private static class Logger implements  ILogger {

        @Override
        public void info(String tag, String msg) {
            System.out.printf("I: <%s> <%s>%n", tag, msg);
        }

        @Override
        public void warn(String tag, String msg) {
            System.out.printf("W: <%s> <%s>%n", tag, msg);
        }

        @Override
        public void error(String tag, String msg) {
            System.out.printf("E: <%s> <%s>%n", tag, msg);
        }

        @Override
        public void exc(Exception e) {
            e.printStackTrace();
        }
    }
}

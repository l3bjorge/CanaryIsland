package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIsland;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenu;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {

    //Canary Island
    void startingScreen(CanaryIsland.ToCanaryIsland presenter);
    void resumingScreen(CanaryIsland.CanaryIslandTo presenter);

    //Home
    void startingScreen(Home.ToHome presenter);
    void resumingScreen(Home.HomeTo presenter);

    //IslandsMenu
    void startingScreen(IslandsMenu.ToIslandsMenu presenter);
    void resumingScreen(IslandsMenu.IslandsMenuTo presenter);

  }

  interface Navigation {

    //Canary Island
    void goToNextScreen(CanaryIsland.CanaryIslandTo presenter);
    void backToPreviousScreen(CanaryIsland.CanaryIslandTo presenter);

    //Home
    void goToNextScreen(Home.HomeTo presenter);
    void backToPreviousScreen(Home.HomeTo presenter);

    //IslandsMenu
    void goToNextScreen(IslandsMenu.IslandsMenuTo presenter);
    void backToPreviousScreen(IslandsMenu.IslandsMenuTo presenter);

  }
}

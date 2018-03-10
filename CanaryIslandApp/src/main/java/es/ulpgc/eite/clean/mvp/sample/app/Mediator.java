package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIsland;
import es.ulpgc.eite.clean.mvp.sample.home.Home;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {

    void startingScreen(CanaryIsland.ToCanaryIsland presenter);
    void resumingScreen(CanaryIsland.CanaryIslandTo presenter);

    void startingScreen(Home.ToHome presenter);
    void resumingScreen(Home.HomeTo presenter);

  }

  interface Navigation {
    void goToNextScreen(CanaryIsland.CanaryIslandTo presenter);
    void backToPreviousScreen(CanaryIsland.CanaryIslandTo presenter);

    void goToNextScreen(Home.HomeTo presenter);
    void backToPreviousScreen(Home.HomeTo presenter);



  }
}

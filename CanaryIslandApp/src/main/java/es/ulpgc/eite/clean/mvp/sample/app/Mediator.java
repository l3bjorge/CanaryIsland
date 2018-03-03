package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIsland;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {

    void startingScreen(CanaryIsland.ToCanaryIsland presenter);
    void resumingScreen(CanaryIsland.CanaryIslandTo presenter);

  }

  interface Navigation {
    void goToNextScreen(CanaryIsland.CanaryIslandTo presenter);
    void backToPreviousScreen(CanaryIsland.CanaryIslandTo presenter);

  }
}

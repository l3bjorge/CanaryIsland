package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIsland;
import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIslandView;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.home.HomeView;

public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private CanaryIslandState toCanaryIslandState, canaryislandToState;
  private HomeState toHomeState, homeToState;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling creatingInitialState()");
    toCanaryIslandState = new CanaryIslandState();
    toCanaryIslandState.toolbarVisibility = false;
    toCanaryIslandState.textVisibility = false;


    Log.d(TAG, "calling creatingInitialHelloState()");
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////

  // CanaryIsland Screen

  @Override
  public void startingScreen(CanaryIsland.ToCanaryIsland presenter){
    if(toCanaryIslandState != null) {
      Log.d(TAG, "calling settingInitialState()");
      presenter.setToolbarVisibility(toCanaryIslandState.toolbarVisibility);
      presenter.setTextVisibility(toCanaryIslandState.textVisibility);

      Log.d(TAG, "calling removingInitialState()");
      toCanaryIslandState = null;
    }

    if(canaryislandToState != null) {
      Log.d(TAG, "calling settingUpdatedState()");
      presenter.setToolbarVisibility(canaryislandToState.toolbarVisibility);
      presenter.setTextVisibility(canaryislandToState.textVisibility);

      Log.d(TAG, "calling removingUpdateState()");
      canaryislandToState = null;
    }

    presenter.onScreenStarted();
  }


  @Override
  public void resumingScreen(CanaryIsland.CanaryIslandTo presenter){
    if(canaryislandToState != null) {
      Log.d(TAG, "calling resumingScreen()");
      Log.d(TAG, "calling restoringUpdatedState()");
      presenter.setToolbarVisibility(canaryislandToState.toolbarVisibility);
      presenter.setTextVisibility(canaryislandToState.textVisibility);

      Log.d(TAG, "calling removingUpdatedState()");
      canaryislandToState = null;
    }

    presenter.onScreenResumed();
  }

    // Home Screen

    @Override
    public void startingScreen(Home.ToHome presenter){
        if(toHomeState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toHomeState.toolbarVisibility);
            presenter.setTextVisibility(toHomeState.textVisibility);

            Log.d(TAG, "calling removingInitialState()");
            toHomeState = null;
        }

        if(homeToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(homeToState.toolbarVisibility);
            presenter.setTextVisibility(homeToState.textVisibility);

            Log.d(TAG, "calling removingUpdateState()");
            homeToState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(Home.HomeTo presenter){
        if(homeToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(homeToState.toolbarVisibility);
            presenter.setTextVisibility(homeToState.textVisibility);

            Log.d(TAG, "calling removingUpdatedState()");
            homeToState = null;
        }

        presenter.onScreenResumed();
    }



    ///////////////////////////////////////////////////////////////////////////////////
  // Navigation ////////////////////////////////////////////////////////////////////

  // CanaryIsland Screen

  @Override
  public void backToPreviousScreen(CanaryIsland.CanaryIslandTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    canaryislandToState = new CanaryIslandState();
      canaryislandToState.textVisibility = true;
      canaryislandToState.toolbarVisibility = false;
  }

  @Override
  public void goToNextScreen(CanaryIsland.CanaryIslandTo presenter) {
      Log.d(TAG, "calling savingUpdatedState()");
      canaryislandToState = new CanaryIslandState();
      canaryislandToState.toolbarVisibility = presenter.isToolbarVisible();
      //canaryislandToState.textVisibility = presenter.isTextVisible();
      canaryislandToState.textVisibility = false;

      Context view = presenter.getManagedContext();
      if (view != null) {
          Log.d(TAG, "calling startingNextScreen()");
          view.startActivity(new Intent(view, CanaryIslandView.class));
          //Log.d(TAG, "calling finishingCurrentScreen()");
          //presenter.destroyView();
      }
  }

      // Home Screen

      @Override
      public void backToPreviousScreen(Home.HomeTo presenter) {
          Log.d(TAG, "calling savingUpdatedState()");
          homeToState = new HomeState();
          homeToState.textVisibility = true;
          homeToState.toolbarVisibility = false;
      }

      @Override
      public void goToNextScreen(Home.HomeTo presenter) {
          Log.d(TAG, "calling savingUpdatedState()");
          homeToState = new HomeState();
          homeToState.toolbarVisibility = presenter.isToolbarVisible();
          //homeToState.textVisibility = presenter.isTextVisible();
          homeToState.textVisibility = false;

          Context view = presenter.getManagedContext();
          if (view != null) {
              Log.d(TAG, "calling startingNextScreen()");
              view.startActivity(new Intent(view, HomeView.class));
              //Log.d(TAG, "calling finishingCurrentScreen()");
              //presenter.destroyView();
          }



      }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class CanaryIslandState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }

  private class HomeState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }



}

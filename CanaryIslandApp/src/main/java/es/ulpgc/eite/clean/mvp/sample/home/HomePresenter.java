package es.ulpgc.eite.clean.mvp.sample.home;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;


public class HomePresenter
    extends GenericPresenter
        <Home.PresenterToView, Home.PresenterToModel, Home.ModelToPresenter, HomeModel>
    implements Home.ViewToPresenter, Home.ModelToPresenter, Home.HomeTo, Home.ToHome {

  private boolean toolbarVisible, englishButtonClicked, spanishButtonClicked, germanButtonClicked;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Home.PresenterToView view) {
    super.onCreate(HomeModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingScreen()");
    Mediator.Lifecycle mediator = (Mediator.Lifecycle) getApplication();
    mediator.startingScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Home.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    Mediator.Lifecycle mediator = (Mediator.Lifecycle) getApplication();
    mediator.resumingScreen(this);
  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");

    Log.d(TAG, "calling backToPreviousScreen()");
    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.backToPreviousScreen(this);
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);

    if(isChangingConfiguration) {
      Log.d(TAG, "calling onChangingConfiguration()");
    } else {
      Log.d(TAG, "calling onDestroy()");
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  /**
   * Method that we call when we click on the button of Spanish to choose language,
   * in this method, Spanish is set as the language of the application and it takes us to the screen to choose an island.
   */

  @Override
  public void onSpanishButtonClicked() {
    Log.d(TAG, "calling onGoToIslandsMenuBtnClicked()");
    if(isViewRunning()) {
      spanishButtonClicked = true;
      englishButtonClicked = false;
      germanButtonClicked = false;
      /*MediatorApp mediator = new MediatorApp();
      mediator.goToIslandsMenuScreen(this);*/
      Mediator.Navigation media = (Mediator.Navigation) getApplication();
      media.goToIslandsMenuScreen(this);
    }

  }

  /**
   * Method that we call when we click on the button of German to choose language,
   * in this method, German is set as the language of the application and it takes us to the screen to choose an island.
   */
  @Override
  public void onGermanButtonClicked() {
    if(isViewRunning()) {
      germanButtonClicked = true;
      englishButtonClicked = false;
      spanishButtonClicked = false;
      Mediator.Navigation media = (Mediator.Navigation) getApplication();
      media.goToIslandsMenuScreen(this);

    }
  }

  /**
   * Method that we call when we click on the button of English to choose language,
   * in this method, English is set as the language of the application and it takes us to the screen to choose an island.
   */
  @Override
  public void onEnglishButtonClicked() {
    Log.d(TAG, "calling onGoToIslandsMenuBtnClicked()");
    if(isViewRunning()) {
      englishButtonClicked = true;
      spanishButtonClicked = false;
      germanButtonClicked = false;
      /*MediatorApp mediator = new MediatorApp();
      mediator.goToIslandsMenuScreen(this);*/
      Mediator.Navigation media = (Mediator.Navigation) getApplication();
      media.goToIslandsMenuScreen(this);
    }

  }




  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  /**
   * Method that set the visibility of the toolbar
   * @param visible is a boolean that if true the toolbar will be visible and if the false is not visible
   */

  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Home //////////////////////////////////////////////////////////////////////

  /**
   * Method that defines what to do when creating the screen
   */
  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  /**
   * Method that defines what to do when resuming the screen
   */
  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Home To //////////////////////////////////////////////////////////////////////

  /**
   *
   * @return
   */
  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  /**
   * Method that defines what to do when destroy the screen
   */
  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  /**
   * Method that defines the visibility of the toolbar
   * @return if the toolbar is visible
   */
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  /**
   * Method that gives us the chosen language when clicking on any of the language
   * @return the selected language
   */
  @Override
  public String checkLanguage() {
    if(spanishButtonClicked){
      return "Spanish";
    } else if(englishButtonClicked){
      return "English";
    } else if (germanButtonClicked){
      return "German";
    }
    return "Error";
  }



  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

    if(isViewRunning()) {
      getView().setLabel(getModel().getLabel(), getModel().getTextEspa(), getModel().getTextEngl(), getModel().getTextDeut());
    }
    checkToolbarVisibility();
  }


  private void checkToolbarVisibility(){
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

}

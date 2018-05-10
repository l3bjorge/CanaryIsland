package es.ulpgc.eite.clean.mvp.sample.islandsmenu;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;


public class IslandsMenuPresenter
    extends GenericPresenter
        <IslandsMenu.PresenterToView, IslandsMenu.PresenterToModel, IslandsMenu.ModelToPresenter, IslandsMenuModel>
    implements IslandsMenu.ViewToPresenter, IslandsMenu.ModelToPresenter, IslandsMenu.IslandsMenuTo, IslandsMenu.ToIslandsMenu {

  private boolean toolbarVisible;
  private boolean gcButtonClicked, tnfButtonClicked, lnzButtonClicked, ftvButtonClicked, plmButtonClicked, gmrButtonClicked, hrrButtonClicked;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(IslandsMenu.PresenterToView view) {
    super.onCreate(IslandsMenuModel.class, this);
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
  public void onResume(IslandsMenu.PresenterToView view) {
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


  @Override
  public void onGcButtonClicked() {
    Log.d(TAG, "calling onGcButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = true;
      tnfButtonClicked = false;
      lnzButtonClicked = false;
      ftvButtonClicked = false;
      plmButtonClicked = false;
      gmrButtonClicked = false;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }


  }

  @Override
  public void onTnfButtonClicked() {
    Log.d(TAG, "calling onTnfButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = true;
      lnzButtonClicked = false;
      ftvButtonClicked = false;
      plmButtonClicked = false;
      gmrButtonClicked = false;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  @Override
  public void onLnzButtonClicked() {
    Log.d(TAG, "calling onLnzButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = false;
      lnzButtonClicked = true;
      ftvButtonClicked = false;
      plmButtonClicked = false;
      gmrButtonClicked = false;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  @Override
  public void onFtvButtonClicked() {
    Log.d(TAG, "calling onFtvButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = false;
      lnzButtonClicked = false;
      ftvButtonClicked = true;
      plmButtonClicked = false;
      gmrButtonClicked = false;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  @Override
  public void onPlmButtonClicked() {
    Log.d(TAG, "calling onPlmButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = false;
      lnzButtonClicked = false;
      ftvButtonClicked = false;
      plmButtonClicked = true;
      gmrButtonClicked = false;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  @Override
  public void onGmrButtonClicked() {
    Log.d(TAG, "calling onGmrButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = false;
      lnzButtonClicked = false;
      ftvButtonClicked = false;
      plmButtonClicked = false;
      gmrButtonClicked = true;
      hrrButtonClicked = false;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  @Override
  public void onHrrButtonClicked() {
    Log.d(TAG, "calling onHrrButtonClicked()");
    if(isViewRunning()) {
      gcButtonClicked = false;
      tnfButtonClicked = false;
      lnzButtonClicked = false;
      ftvButtonClicked = false;
      plmButtonClicked = false;
      gmrButtonClicked = false;
      hrrButtonClicked = true;
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToCategoryScreen(this);
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////
  // Model To Presenter //////////////////////////////////////////////////////////////

  @Override
  public String getLanguage(){
    Mediator.Navigation media = (Mediator.Navigation) getApplication();
    String language = media.getLanguage();
    return language;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setLanguage(String language) {
    getModel().setLanguage(language);
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Hello //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
  }

  @Override
  public String checkIsland() {
    if (gcButtonClicked){
      return "GranCanaria";
    } else if (tnfButtonClicked){
      return "Tenerife";
    } else if (lnzButtonClicked){
      return "Lanzarote";
    } else if (ftvButtonClicked){
      return "Fuerteventura";
    } else if (plmButtonClicked){
      return "LaPalma";
    } else if (gmrButtonClicked){
      return "LaGomera";
    } else if (hrrButtonClicked){
      return "ElHierro";
    }
    return "Error";
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Hello To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

    if(isViewRunning()) {
      getView().setTextaso(getModel().getTittle());

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

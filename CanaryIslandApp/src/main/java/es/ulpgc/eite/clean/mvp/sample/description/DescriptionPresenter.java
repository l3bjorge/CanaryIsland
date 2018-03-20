package es.ulpgc.eite.clean.mvp.sample.description;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class DescriptionPresenter
    extends GenericPresenter
        <Description.PresenterToView, Description.PresenterToModel, Description.ModelToPresenter, es.ulpgc.eite.clean.mvp.sample.description.DescriptionModel>
    implements Description.ViewToPresenter, Description.ModelToPresenter, Description.DescriptionTo, Description.ToDescription, Presenter<es.ulpgc.eite.clean.mvp.sample.description.Description.PresenterToView> {

  private boolean toolbarVisible;
  private boolean tittleVisible, descriptionVisible, textVisibility;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Description.PresenterToView view) {
    super.onCreate(DescriptionModel.class, this);
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
  public void onResume(Description.PresenterToView view) {
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
  public void onButtonClicked() {
    Log.d(TAG, "calling onButtonClicked()");


  }

  @Override
  public void onBtnLocationCliked() {
    Log.d(TAG, "calling onBtnLocationCliked()");
    if(isViewRunning()){
      Uri location = getModel().getLocation();

      Log.d(TAG, "calling goToGoogleMaps");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToGoogleMaps(this);
      return;
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setTittleVisibility(boolean visible) {
    tittleVisible = visible;
  }

  @Override
  public void setDescriptionVisibility(boolean visible) {
    descriptionVisible = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Hello //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  @Override
  public void setTextVisibility(boolean textVisibility) {
    this.textVisibility = textVisibility;
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
    getView().setTittle(getModel().getTittle());
    getView().setTittle(getModel().getDescription());
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

  @Override
  public boolean isTittleVisible() {
    return tittleVisible;
  }

  @Override
  public boolean isDescriptionVisible() {
    return descriptionVisible;
  }


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

    if(isViewRunning()) {

    }
    checkToolbarVisibility();
    checkTittleVisibility();
    checkDescriptionVisibility();
  }

  private void checkDescriptionVisibility() {
    if(isViewRunning()) {
      if (!descriptionVisible) {
        getView().hideDescription();
      } else {
        getView().showDescription();
      }
    }
  }

  private void checkToolbarVisibility(){
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

  private void checkTittleVisibility(){
    if(isViewRunning()) {
      if(!tittleVisible) {
        getView().hideTittle();
      } else {
        getView().showTittle();
      }
    }
  }

}

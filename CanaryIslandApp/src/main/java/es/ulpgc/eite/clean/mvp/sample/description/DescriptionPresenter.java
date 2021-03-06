package es.ulpgc.eite.clean.mvp.sample.description;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;

public class DescriptionPresenter
    extends GenericPresenter
        <Description.PresenterToView, Description.PresenterToModel, Description.ModelToPresenter, es.ulpgc.eite.clean.mvp.sample.description.DescriptionModel>
    implements Description.ViewToPresenter, Description.ModelToPresenter, Description.DescriptionTo, Description.ToDescription, Presenter<es.ulpgc.eite.clean.mvp.sample.description.Description.PresenterToView> {

  private boolean toolbarVisible;
  private LocationItem selectedItem;
  private boolean tittleVisible, descriptionVisible, locationBtnVisibity, webBtnVisibity, faceBtnVisibity, instaBtnVisibity;



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
    tittleVisible = true;
    descriptionVisible = true;
    faceBtnVisibity = true;
    instaBtnVisibity = true;
    webBtnVisibity = true;
    locationBtnVisibity = true;



    if (selectedItem.getDbItem().getMap() == null){
      locationBtnVisibity = false;
    }

    if (selectedItem.getDbItem().getWeb() == null){
      webBtnVisibity = false;
    }

    if (selectedItem.getDbItem().getFacebook() == null){
      faceBtnVisibity = false;
    }

    if (selectedItem.getDbItem().getInstagram() == null){
      instaBtnVisibity = false;
    }

    String language = getLanguage();
    getView().setDescription(selectedItem.getDescription(language));


    checkTittleVisibility();
    checkDescriptionVisibility();
    checkLocationBtnVisibility();
    checkWebBtnVisibility();
    checkFaceBtnVisibility();
    checkInstaBtnVisibility();
    getView().setTittle(selectedItem.getTitle());

    getModel().setFacebook(selectedItem.getDbItem().getFacebook());
    getModel().setIdFacebook(selectedItem.getDbItem().getFaceurl());
    getModel().setInstagram(selectedItem.getDbItem().getInstagramurl());
    getModel().setIdInstagram(selectedItem.getDbItem().getInstagram());
    getModel().setWeb(selectedItem.getDbItem().getWeb());
    getModel().setLocation(selectedItem.getDbItem().getMap());





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

    tittleVisible = true;
    descriptionVisible = true;


    checkTittleVisibility();
    checkDescriptionVisibility();
    checkLocationBtnVisibility();
    checkWebBtnVisibility();
    checkFaceBtnVisibility();
    checkInstaBtnVisibility();
    getView().setTittle(selectedItem.getTitle());
    String language = getLanguage();
    getView().setDescription(selectedItem.getDescription(language));
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
   *Method that open google Maps when clicked to show the location
   */
  @Override
  public void onBtnLocationCliked() {
    Log.d(TAG, "calling onBtnLocationCliked()");
    if(isViewRunning()){
      Log.d(TAG, "calling goToGoogleMaps");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToGoogleMaps(getModel().getLocation());

    }
  }

  /**
   *Method that open a web browser when clicked to show the web page of the location
   */
  @Override
  public void onBtnWebCliked() {
    Log.d(TAG, "calling onBtnWebCliked()");
    if(isViewRunning()){
      Log.d(TAG, "calling onBtnWebCliked()");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToWeb(getModel().getWeb());

    }
  }

  /**
   *Method that open facebook app or a web browser when clicked to show the facebook page of the location
   */
  @Override
  public void onBtnFacebookClicked() {
    Log.d(TAG, "calling onBtnFacebookClicked()");
    if(isViewRunning()){
      Log.d(TAG, "calling onBtnFacebookClicked()");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToFacebook(getModel().getFacebook(), getModel().getIdFacebook());
    }
  }

  /**
   *Method that open instagram app or a web browser when clicked to show the instagram page of the location
   */
  @Override
  public void onBtnInstagramClicked() {
    Log.d(TAG, "calling onBtnInstagramClicked()");
    if(isViewRunning()){
      Log.d(TAG, "onBtnInstagramClicked()");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToInstagram(getModel().getIdInstagram(), getModel().getInstagram());

    }
  }

  /**
   * Method that show a task that this location is add to
   */
  @Override
  public void onBtnLikeCliked() {
    Log.d(TAG, "calling onBtnLikeCliked()");
    if(isViewRunning()){
      Log.d(TAG, "onBtnLikeCliked()");

      String language = getLanguage();
      String message;

      if(language.equals("Spanish")){
        message = "Lugar Añadido a Favoritos";
        getView().displayShortMessage(message);
      } else if (language.equals("English")){
        message = "Add Favorite Places";
        getView().displayShortMessage(message);
      } else if (language.equals("German")){

      }






    }
  }

    /**
     * Method that share this location
     */
  @Override
  public void onBtnShareClicked(){
    Log.d(TAG, "calling onBtnShareClicked()");

    if(isViewRunning()){
      Log.d(TAG, "calling onBtnShareClicked()");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.share(selectedItem.getTitle(), getModel().getImage(), getModel().getLocation());
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////
  // Model To Presenter //////////////////////////////////////////////////////////////

    /**
     * Method that get the language that is select in the home screen
     * @return language select in the home screen
     */
  @Override
  public String getLanguage(){
    Mediator.Navigation media = (Mediator.Navigation) getApplication();
    return media.getLanguage();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


  /**
   *
   * @param visible
   */
  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setSelectedItem(LocationItem selectedItem) {
    this.selectedItem = selectedItem;
  }

  /**
   *
   * @param visible
   */
  @Override
  public void setTittleVisibility(boolean visible) {
    tittleVisible = visible;
  }

  /**
   *
   * @param visible
   */
  @Override
  public void setDescriptionVisibility(boolean visible) {
    descriptionVisible = visible;
  }

  @Override
  public void setLocationBtnVisibility(boolean visible) {
    locationBtnVisibity = visible;
  }

  @Override
  public void setWebBtnVisiblity(boolean visible) {
    webBtnVisibity = visible;
  }

  @Override
  public void setFaceBtnVisiblity(boolean visible) {
    faceBtnVisibity = visible;
  }

  @Override
  public void setInstaBtnVisiblity(boolean visible) {
    instaBtnVisibity = visible;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Hello //////////////////////////////////////////////////////////////////////

  /**
   *
   */
  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
    getModel().setImage(selectedItem.getDbItem().getImage());
    getView().setImage(getModel().getImage());
  }


  /**
   *
   */
  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
    getView().setTittle(getModel().getTittle());
    getView().setDescription(getModel().getDescription());
    getModel().setImage(selectedItem.getDbItem().getImage());
    getView().setImage(getModel().getImage());

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Hello To //////////////////////////////////////////////////////////////////////

  /**
   *
   * @return
   */
  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  /**
   *
   */
  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isTittleVisible() {
    return tittleVisible;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isDescriptionVisible() {
    return descriptionVisible;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isLocationBtnVisible() {
    return locationBtnVisibity;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isWebBtnVisible() {
    return webBtnVisibity;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isFaceBtnVisible() {
    return faceBtnVisibity;
  }

  /**
   *
   * @return
   */
  @Override
  public boolean isInstaBtnVisible() {
    return instaBtnVisibity;
  }


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

    if(isViewRunning()) {

    }
    checkToolbarVisibility();
    checkTittleVisibility();
    checkDescriptionVisibility();
    checkLocationBtnVisibility();
    checkWebBtnVisibility();
    checkFaceBtnVisibility();
    checkInstaBtnVisibility();
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

  private void checkLocationBtnVisibility(){
    if(isViewRunning()) {
      if(!locationBtnVisibity) {
        getView().hideLocationBtn();
      } else {
        getView().showLocationBtn();
      }
    }
  }

  private void checkWebBtnVisibility(){
    if(isViewRunning()) {
      if(!webBtnVisibity) {
        getView().hideWebBtn();
      } else {
        getView().showWebBtn();
      }
    }
  }

  private void checkFaceBtnVisibility(){
    if(isViewRunning()) {
      if(!faceBtnVisibity) {
        getView().hideFaceBtn();
      } else {
        getView().showFaceBtn();
      }
    }
  }

  private void checkInstaBtnVisibility(){
    if(isViewRunning()) {
      if(!instaBtnVisibity) {
        getView().hideInstaBtn();
      } else {
        getView().showInstaBtn();
      }
    }
  }

}

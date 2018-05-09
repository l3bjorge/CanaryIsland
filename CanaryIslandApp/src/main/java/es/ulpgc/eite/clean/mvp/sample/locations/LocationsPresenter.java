package es.ulpgc.eite.clean.mvp.sample.locations;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import java.util.List;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;
import es.ulpgc.eite.clean.mvp.sample.data.MasterDetailData;
import io.realm.Realm;

public class LocationsPresenter
    extends GenericPresenter
        <Locations.PresenterToView, Locations.PresenterToModel, Locations.ModelToPresenter, LocationsModel>
    implements Locations.ViewToPresenter, Locations.ModelToPresenter, Locations.LocationsTo, Locations.ToLocations {

  private boolean hideToolbar, hideProgress;
  private ModelItem selectedItem;
  private LocationItem location;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Locations.PresenterToView view) {
    super.onCreate(LocationsModel.class, this);
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
  public void onResume(Locations.PresenterToView view) {
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

  /////////////////////////////////////////////////////////////////////////////////////
  // Model To Presenter //////////////////////////////////////////////////////////////

  /**
   * Llamado desde el modelo cuando finaliza la tarea para la obtención del contenido de la lista
   *
   * @param items como contenido de la lista a mostrar en pantalla
   */
  @Override
  public void onLoadItemsTaskFinished(List<LocationItem> items) {
    Log.d(TAG, "calling onLoadItemsTaskFinished()");

    // Una vez finaliza la tarea para la obtención del contenido de la lista,
    // hacemos desaparecer de pantalla el círculo de progreso
    // y actualizamos el contenido de la lista
    hideProgress = true;
    checkVisibility();
    getView().setRecyclerAdapterContent(items);
  }

  /**
   * Llamado desde el modelo cuando comienza la tarea para la obtención del contenido de la lista
   */
  @Override
  public void onLoadItemsTaskStarted() {
    Log.d(TAG, "calling onLoadItemsTaskStarted()");

    hideProgress = false;
    checkVisibility();
  }

  @Override
  public String getLanguage(){
    Mediator.Navigation media = (Mediator.Navigation) getApplication();
    return media.getLanguage();
  }

    @Override
    public LocationItem getLocation(){
       return location;
    }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  /**
   * Llamado desde la vista cada vez que se reinicia el maestro.
   * Esta llamada puede hacerse por giro de pantalla o por finalización del detalle pero,
   * en cualquier caso, habrá que actualizar el contenido de la lista
   */
  @Override
  public void onResumingContent() {
    Log.d(TAG, "calling onResumingContent()");

    // Si la tarea para la obtención del contenido de la lista ha finalizado,
    // el contenido estará disponible inmediatamente, sino habrá que esperar su finalización.
    // En cualquier caso, el presentador será notificado desde el modelo
    Log.d(TAG, "calling loadItems()");
    getModel().loadItems();
  }


  @Override
  public void goToDescriptionScreen(LocationItem item) {
    Log.d(TAG, "calling goToDescriptionScreen()");
    location = item;
    if(isViewRunning()) {
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToDescriptionScreen(this);
    }
  }




  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


  @Override
  public void setToolbarVisibility(boolean visible) {
    hideToolbar = visible;
  }

  @Override
  public void setLanguage(String language) {
    getModel().setLanguage(language);
  }

  @Override
  public void setSelectedItem(ModelItem item) {
    selectedItem = item;
    getModel().setItem(item);
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // To Hello //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
    // Comprobamos si debemos mostrar o no la barra de tareas en función
    // de la orientación actúal de la pantalla
    int screenOrientation = getActivityContext().getResources().getConfiguration().orientation;
    if (hideToolbar || screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
      hideToolbar = true;
    } else {
      hideToolbar = false;
    }
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
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
    return hideToolbar;
  }


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");
    getView().setActionBarTitle(selectedItem.getContent());
    checkVisibility();
  }

  private void checkVisibility(){
    if(isViewRunning()) {
      if (hideProgress) {
        getView().hideProgress();
      } else {
        getView().showProgress();
      }
    }
  }
}


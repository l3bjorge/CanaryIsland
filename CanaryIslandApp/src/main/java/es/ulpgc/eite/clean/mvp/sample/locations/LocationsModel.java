package es.ulpgc.eite.clean.mvp.sample.locations;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;
import es.ulpgc.eite.clean.mvp.sample.data.MasterDetailData;
import io.realm.Realm;
import io.realm.RealmList;


public class LocationsModel
    extends GenericModel<Locations.ModelToPresenter> implements Locations.PresenterToModel {


  private static final int ITEM_COUNT = 9;

  private String label;
  private List<LocationItem> items;
  private List<ModelItem> beaches = null;
  private List<ModelItem> parks = null;
  private List<ModelItem> shoppingCenters = null;
  private List<ModelItem> museums = null;
  private List<ModelItem> restaurants = null;
  private List<ModelItem> touristicSpots = null;
  private List<ModelItem> nightClubs = null;
  private List<ModelItem> theates = null;
  private List<ModelItem> culturalSpots = null;


  private List<List<ModelItem>> itemsSet = null;
  private String position = null;
  private boolean runningTask;
  private boolean validDatabase;
  private ModelItem selecteditem;
  private String errorMsg;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Locations.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");
    label = "Choose your category";
  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////


  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public void setItem(ModelItem item) {
    selecteditem = item;
  }


  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Si el contenido ya ha sido fijado antes, se notificará inmediatamente al presentador y,
   * sino es el caso, la notificación se realizará al finalizar la tarea que fija este contenido
   */
  @Override
  public void loadItems() {
    if(!validDatabase && !runningTask) {
      startDelayedTask();

    } else if(!runningTask){
      Log.d(TAG, "calling onLoadItemsTaskFinished() method");
      getPresenter().onLoadItemsTaskFinished(items);

    } else {
      Log.d(TAG, "calling onLoadItemsTaskStarted() method");
      getPresenter().onLoadItemsTaskStarted();
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////

  /**
   * Llamado para recuperar los elementos iniciales de la lista.
   * En este caso siempre se llamará a la tarea asíncrona
   */
  @Override
  public void reloadItems() {
    MasterDetailData.deleteAllDatabaseItems();
    validDatabase = false;
    loadItems();
  }

  @Override
  public void setDatabaseValidity(boolean valid) {
    validDatabase = valid;
  }


  @Override
  public String getErrorMessage() {
    return errorMsg;
  }

  private void startDelayedTask() {
    Log.d(TAG, "calling startDelayedTask() method");
    runningTask = true;
    Log.d(TAG, "calling onLoadItemsTaskStarted() method");
    getPresenter().onLoadItemsTaskStarted();

    // Mock Hello: A handler to delay the answer
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Log.d(TAG, "calling loadItemsFromJsonFile() method");
        if (MasterDetailData.getItemsFromDatabase().size() == 0) {
          MasterDetailData.loadItemsFromJsonFile
                  (getPresenter().getManagedContext(), "locations.json");
        }

        runningTask = false;
        validDatabase = true;
        Log.d(TAG, "calling onLoadItemsTaskFinished() method");
        items = MasterDetailData.getItemsFromDatabase();
        for ( int i = 0; i < items.size(); i++){
      if (!items.get(i).getCategory().equals(selecteditem.getContent())){
        items.remove(i);
        i = i-1;
      }
    }
        getPresenter().onLoadItemsTaskFinished(items);

      }
    }, 1000);
  }
}


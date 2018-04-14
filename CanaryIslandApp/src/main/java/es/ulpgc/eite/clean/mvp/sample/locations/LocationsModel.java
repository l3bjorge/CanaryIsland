package es.ulpgc.eite.clean.mvp.sample.locations;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;


public class LocationsModel
    extends GenericModel<Locations.ModelToPresenter> implements Locations.PresenterToModel {


  private static final int ITEM_COUNT = 9;

  private String label;
  private List<ModelItem> beaches = null;
  private List<ModelItem> parks = null;
  private List<ModelItem> shoppingCenters = null;
  private List<List<ModelItem>> itemsSet = null;
  private String position = null;
  private boolean runningTask;
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
    if ( !runningTask) {

      startDelayedTask();
    } else {
      if (!runningTask) {
        getPresenter().onLoadItemsTaskFinished(itemsSet.get(Integer.parseInt(position)));
      } else {
        getPresenter().onLoadItemsTaskStarted();
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////



  private String makeDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Item: ").append(position).append("\n");
    for (int count = 0; count < position; count++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  private void setItems() {
    itemsSet = new ArrayList();
    beaches = new ArrayList();
    parks= new ArrayList();
    shoppingCenters= new ArrayList();


    beaches.add(new ModelItem(String.valueOf(0), "Las Canteras", makeDetails(0)));
    beaches.add(new ModelItem(String.valueOf(1), "Alcaravaneras", makeDetails(1)));
    beaches.add(new ModelItem(String.valueOf(2), "Playa del Inglés", makeDetails(2)));
    beaches.add(new ModelItem(String.valueOf(3), "Beach 4", makeDetails(3)));
    beaches.add(new ModelItem(String.valueOf(4), "Beach 5", makeDetails(4)));
    beaches.add(new ModelItem(String.valueOf(5), "Beach 6", makeDetails(5)));
    beaches.add(new ModelItem(String.valueOf(6), "Beach 7", makeDetails(6)));
    beaches.add(new ModelItem(String.valueOf(7), "Beach 8", makeDetails(7)));
    beaches.add(new ModelItem(String.valueOf(8), "Beach 9", makeDetails(8)));
    beaches.add(new ModelItem(String.valueOf(9), "Beach 10", makeDetails(9)));

    itemsSet.add(beaches);

    parks.add(new ModelItem(String.valueOf(0), "Las Rehoyas", makeDetails(0)));
    parks.add(new ModelItem(String.valueOf(1), "Juan Pablo II", makeDetails(1)));
    parks.add(new ModelItem(String.valueOf(2), "Doramas", makeDetails(2)));
    parks.add(new ModelItem(String.valueOf(3), "Park 4 ", makeDetails(3)));
    parks.add(new ModelItem(String.valueOf(4), "Park 5", makeDetails(4)));
    parks.add(new ModelItem(String.valueOf(5), "Park 6", makeDetails(5)));
    parks.add(new ModelItem(String.valueOf(6), "Park 7", makeDetails(6)));
    parks.add(new ModelItem(String.valueOf(7), "Park 8", makeDetails(7)));
    parks.add(new ModelItem(String.valueOf(8), "Park 9", makeDetails(8)));
    parks.add(new ModelItem(String.valueOf(9), "Activities", makeDetails(9)));

    itemsSet.add(parks);

    shoppingCenters.add(new ModelItem(String.valueOf(0), "Las Arenas", makeDetails(0)));
    shoppingCenters.add(new ModelItem(String.valueOf(1), "Las terrazas", makeDetails(1)));
    shoppingCenters.add(new ModelItem(String.valueOf(2), "El Mirador", makeDetails(2)));
    shoppingCenters.add(new ModelItem(String.valueOf(3), "Atlantico Vecindario ", makeDetails(3)));
    shoppingCenters.add(new ModelItem(String.valueOf(4), "El Tablero", makeDetails(4)));
    shoppingCenters.add(new ModelItem(String.valueOf(5), "Los Alisios", makeDetails(5)));
    shoppingCenters.add(new ModelItem(String.valueOf(6), "La Ballena", makeDetails(6)));
    shoppingCenters.add(new ModelItem(String.valueOf(7), "El Muelle", makeDetails(7)));
    shoppingCenters.add(new ModelItem(String.valueOf(8), "7 Palmas", makeDetails(8)));
    shoppingCenters.add(new ModelItem(String.valueOf(9), "Las Ramblas", makeDetails(9)));

    itemsSet.add(shoppingCenters);

  }

  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Consiste en una tarea asíncrona que retrasa un tiempo la obtención del contenido.
   * El modelo notificará al presentador cuando se inicia y cuando finaliza esta tarea.
   */
  private void startDelayedTask() {
    Log.d(TAG, "calling startDelayedTask()");
    runningTask = true;
    getPresenter().onLoadItemsTaskStarted();
    position = selecteditem.getId();


    // Mock Hello: A handler to delay the answer
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        setItems();
        runningTask = false;
        getPresenter().onLoadItemsTaskFinished(itemsSet.get(Integer.parseInt(position)));
      }
    }, 1000);
  }
}


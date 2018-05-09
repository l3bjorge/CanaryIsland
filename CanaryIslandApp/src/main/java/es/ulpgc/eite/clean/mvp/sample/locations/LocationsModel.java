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
      getPresenter().onLoadItemsTaskFinished(MasterDetailData.getItemsFromDatabase());

    } else {
      Log.d(TAG, "calling onLoadItemsTaskStarted() method");
      getPresenter().onLoadItemsTaskStarted();
    }
  }



  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Si el contenido ya ha sido fijado antes, se notificará inmediatamente al presentador y,
   * sino es el caso, la notificación se realizará al finalizar la tarea que fija este contenido
   */
  /*@Override
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
  }*/

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


/*
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
    museums= new ArrayList();
    restaurants= new ArrayList();
    touristicSpots= new ArrayList();
    nightClubs= new ArrayList();
    theates= new ArrayList();
    culturalSpots= new ArrayList();




    beaches.add(new ModelItem(String.valueOf(0), "Las Canteras", makeDetails(0)));
    beaches.add(new ModelItem(String.valueOf(1), "Alcaravaneras", makeDetails(1)));
    beaches.add(new ModelItem(String.valueOf(2), "Playa del Inglés", makeDetails(2)));
    beaches.add(new ModelItem(String.valueOf(3), "Playa del Faro", makeDetails(3)));
    beaches.add(new ModelItem(String.valueOf(4), "Amodores", makeDetails(4)));
    beaches.add(new ModelItem(String.valueOf(5), "Anfi del Mar", makeDetails(5)));
    beaches.add(new ModelItem(String.valueOf(6), "Playa de Mogan", makeDetails(6)));
    beaches.add(new ModelItem(String.valueOf(7), "Playa de San Agustín", makeDetails(7)));
    beaches.add(new ModelItem(String.valueOf(8), "Playa de Güigüí", makeDetails(8)));
    beaches.add(new ModelItem(String.valueOf(9), "Playa de las Burras", makeDetails(9)));

    itemsSet.add(beaches);

    parks.add(new ModelItem(String.valueOf(0), "Las Rehoyas", makeDetails(0)));
    parks.add(new ModelItem(String.valueOf(1), "Juan Pablo II", makeDetails(1)));
    parks.add(new ModelItem(String.valueOf(2), "Doramas", makeDetails(2)));
    parks.add(new ModelItem(String.valueOf(3), "Parque Sur ", makeDetails(3)));
    parks.add(new ModelItem(String.valueOf(4), "San Telmo", makeDetails(4)));
    parks.add(new ModelItem(String.valueOf(5), "Santa Catalina", makeDetails(5)));
    parks.add(new ModelItem(String.valueOf(6), "Palmitos Park", makeDetails(6)));
    parks.add(new ModelItem(String.valueOf(7), "San Cristóbal", makeDetails(7)));
    parks.add(new ModelItem(String.valueOf(8), "Parque Romano", makeDetails(8)));
    parks.add(new ModelItem(String.valueOf(9), "Parque Natural Pilancones", makeDetails(9)));

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

    museums.add(new ModelItem(String.valueOf(0), "Pérez Galdós", makeDetails(0)));
    museums.add(new ModelItem(String.valueOf(1), "Casa de Colón", makeDetails(1)));
    museums.add(new ModelItem(String.valueOf(2), "Leon y Castillo", makeDetails(2)));
    museums.add(new ModelItem(String.valueOf(3), "Cueva Pintada ", makeDetails(3)));
    museums.add(new ModelItem(String.valueOf(4), "Elder de la Ciencia", makeDetails(4)));
    museums.add(new ModelItem(String.valueOf(5), "Néstor", makeDetails(5)));
    museums.add(new ModelItem(String.valueOf(6), "Diocesano de Arte Sacro", makeDetails(6)));
    museums.add(new ModelItem(String.valueOf(7), "Museo del Vino", makeDetails(7)));
    museums.add(new ModelItem(String.valueOf(8), "Museo Naval", makeDetails(8)));
    museums.add(new ModelItem(String.valueOf(9), "Poeta Domingo Rivero", makeDetails(9)));

    itemsSet.add(museums);

    restaurants.add(new ModelItem(String.valueOf(0), "Wapa Tapa", makeDetails(0)));
    restaurants.add(new ModelItem(String.valueOf(1), "Valle de Mogan", makeDetails(1)));
    restaurants.add(new ModelItem(String.valueOf(2), "El Capitan", makeDetails(2)));
    restaurants.add(new ModelItem(String.valueOf(3), "Adio Mare ", makeDetails(3)));
    restaurants.add(new ModelItem(String.valueOf(4), "El Churrasco", makeDetails(4)));
    restaurants.add(new ModelItem(String.valueOf(5), "El Clandestino", makeDetails(5)));
    restaurants.add(new ModelItem(String.valueOf(6), "El Carretón", makeDetails(6)));
    restaurants.add(new ModelItem(String.valueOf(7), "Sakura VI Wok", makeDetails(7)));
    restaurants.add(new ModelItem(String.valueOf(8), "Foster´s Hollywood", makeDetails(8)));
    restaurants.add(new ModelItem(String.valueOf(9), "Tony Roma's", makeDetails(9)));

    itemsSet.add(restaurants);

    touristicSpots.add(new ModelItem(String.valueOf(0), "Roque Nublo", makeDetails(0)));
    touristicSpots.add(new ModelItem(String.valueOf(1), "Agaete", makeDetails(1)));
    touristicSpots.add(new ModelItem(String.valueOf(2), "Galdar", makeDetails(2)));
    touristicSpots.add(new ModelItem(String.valueOf(3), "Maspalomas ", makeDetails(3)));
    touristicSpots.add(new ModelItem(String.valueOf(4), "Mogan", makeDetails(4)));
    touristicSpots.add(new ModelItem(String.valueOf(5), "Teror", makeDetails(5)));
    touristicSpots.add(new ModelItem(String.valueOf(6), "Tamadaba", makeDetails(6)));
    touristicSpots.add(new ModelItem(String.valueOf(7), "touristicSpots7", makeDetails(7)));
    touristicSpots.add(new ModelItem(String.valueOf(8), "touristicSpots8", makeDetails(8)));
    touristicSpots.add(new ModelItem(String.valueOf(9), "touristicSpots", makeDetails(9)));

    itemsSet.add(touristicSpots);

    nightClubs.add(new ModelItem(String.valueOf(0), "Chester", makeDetails(0)));
    nightClubs.add(new ModelItem(String.valueOf(1), "Terraza Kopa", makeDetails(1)));
    nightClubs.add(new ModelItem(String.valueOf(2), "Pacha", makeDetails(2)));
    nightClubs.add(new ModelItem(String.valueOf(3), "El Papagayo ", makeDetails(3)));
    nightClubs.add(new ModelItem(String.valueOf(4), "Sotavento", makeDetails(4)));
    nightClubs.add(new ModelItem(String.valueOf(5), "On the Sea", makeDetails(5)));
    nightClubs.add(new ModelItem(String.valueOf(6), "Aqua Ocean Club", makeDetails(6)));
    nightClubs.add(new ModelItem(String.valueOf(7), "TAO Club & Garden", makeDetails(7)));
    nightClubs.add(new ModelItem(String.valueOf(8), "Dubai Club", makeDetails(8)));
    nightClubs.add(new ModelItem(String.valueOf(9), "ChinaWhite", makeDetails(9)));

    itemsSet.add(nightClubs);

    theates.add(new ModelItem(String.valueOf(0), "Pérez Galdós", makeDetails(0)));
    theates.add(new ModelItem(String.valueOf(1), "Guiniguada", makeDetails(1)));
    theates.add(new ModelItem(String.valueOf(2), "Theatre Cuyás", makeDetails(2)));
    theates.add(new ModelItem(String.valueOf(3), "Theatro3 ", makeDetails(3)));
    theates.add(new ModelItem(String.valueOf(4), "Theatre4", makeDetails(4)));
    theates.add(new ModelItem(String.valueOf(5), "Theatre5", makeDetails(5)));
    theates.add(new ModelItem(String.valueOf(6), "Theatre6", makeDetails(6)));
    theates.add(new ModelItem(String.valueOf(7), "Theatre7", makeDetails(7)));
    theates.add(new ModelItem(String.valueOf(8), "Theatre8", makeDetails(8)));
    theates.add(new ModelItem(String.valueOf(9), "Theatre9", makeDetails(9)));

    itemsSet.add(theates);

    culturalSpots.add(new ModelItem(String.valueOf(0), "Centro Cultural San fernando", makeDetails(0)));
    culturalSpots.add(new ModelItem(String.valueOf(1), "Centro Cultural Cicca", makeDetails(1)));
    culturalSpots.add(new ModelItem(String.valueOf(2), "San Martín Contemporáneo", makeDetails(2)));
    culturalSpots.add(new ModelItem(String.valueOf(3), "Cultural3 ", makeDetails(3)));
    culturalSpots.add(new ModelItem(String.valueOf(4), "Cultural4", makeDetails(4)));
    culturalSpots.add(new ModelItem(String.valueOf(5), "Cultural5", makeDetails(5)));
    culturalSpots.add(new ModelItem(String.valueOf(6), "Cultural6", makeDetails(6)));
    culturalSpots.add(new ModelItem(String.valueOf(7), "Cultural7", makeDetails(7)));
    culturalSpots.add(new ModelItem(String.valueOf(8), "Cultural8", makeDetails(8)));
    culturalSpots.add(new ModelItem(String.valueOf(9), "Cultural9", makeDetails(9)));

    itemsSet.add(culturalSpots);

  }*/

  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Consiste en una tarea asíncrona que retrasa un tiempo la obtención del contenido.
   * El modelo notificará al presentador cuando se inicia y cuando finaliza esta tarea.
   */
  /*private void startDelayedTask() {
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
  }*/

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
        List<LocationItem> items = MasterDetailData.getItemsFromDatabase();
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


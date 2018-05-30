package es.ulpgc.eite.clean.mvp.sample.category;

import android.util.Log;

import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.data.CategoryItem;
import es.ulpgc.eite.clean.mvp.sample.data.MasterDetailData;


public class CategoryModel
    extends GenericModel<Category.ModelToPresenter> implements Category.PresenterToModel {

  private String label;
  private String language;
  private List<CategoryItem> categoryItems;
  private boolean runningTask;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Category.ModelToPresenter presenter) {
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

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public void loadItemsFromDatabase() {
    if (MasterDetailData.getItemsFromDatabase().size() == 0) {
      MasterDetailData.loadItemsFromJsonFile
              (getPresenter().getManagedContext(), "locations.json");
    }

    categoryItems = MasterDetailData.getCategoryItemsFromDatabase();
    //categoryItems will have a list of 100 Items
    //We only need one category for each type of location
    for ( int i = 0; i < categoryItems.size() - 1; i++){

        if (categoryItems.get(i).getSpanishName().equals(categoryItems.get(i + 1).getSpanishName())) {
          categoryItems.remove(i + 1);
          i = i - 1;
        }


    }
    getPresenter().onLoadItemsTaskFinished(categoryItems, language);
  }

  /////////////////////////////////////////////////////////////////////////////////////

  private void setItems() {
//    items = new ArrayList();
//
//    if (language == "English") {
//
//      addItem(new ModelItem(String.valueOf(0), "Beaches"));
//      addItem(new ModelItem(String.valueOf(1), "Parks"));
//      addItem(new ModelItem(String.valueOf(2), "Shopping Centers"));
//      addItem(new ModelItem(String.valueOf(3), "Museums"));
//      addItem(new ModelItem(String.valueOf(4), "Restaurants"));
//      addItem(new ModelItem(String.valueOf(5), "Touristic Spots"));
//      addItem(new ModelItem(String.valueOf(6), "Night Clubs"));
//      addItem(new ModelItem(String.valueOf(7), "Theaters"));
//      addItem(new ModelItem(String.valueOf(8), "Cultural Spots"));
//      addItem(new ModelItem(String.valueOf(9), "Activities"));
//
//    } else if (language == "Spanish") {
//
//      addItem(new ModelItem(String.valueOf(0), "Playas"));
//      addItem(new ModelItem(String.valueOf(1), "Parques"));
//      addItem(new ModelItem(String.valueOf(2), "Centros Comerciales"));
//      addItem(new ModelItem(String.valueOf(3), "Museos"));
//      addItem(new ModelItem(String.valueOf(4), "Restaurantes"));
//      addItem(new ModelItem(String.valueOf(5), "Puntos Turísticos"));
//      addItem(new ModelItem(String.valueOf(6), "Discotecas"));
//      addItem(new ModelItem(String.valueOf(7), "Teatros"));
//      addItem(new ModelItem(String.valueOf(8), "Puntos Culturales"));
//      addItem(new ModelItem(String.valueOf(9), "Actividades"));
//
//    } else if (language == "German") {
//
//      addItem(new ModelItem(String.valueOf(0), "Strände"));
//      addItem(new ModelItem(String.valueOf(1), "Parks"));
//      addItem(new ModelItem(String.valueOf(2), "Einkaufszentren"));
//      addItem(new ModelItem(String.valueOf(3), "Museen"));
//      addItem(new ModelItem(String.valueOf(4), "Gaststätte"));
//      addItem(new ModelItem(String.valueOf(5), "Touristenpunkte"));
//      addItem(new ModelItem(String.valueOf(6), "Nachtclubs"));
//      addItem(new ModelItem(String.valueOf(7), "Theater"));
//      addItem(new ModelItem(String.valueOf(8), "Kulturelle Punkte"));
//      addItem(new ModelItem(String.valueOf(9), "Aktivitäten"));
//
//    }

  }

  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Consiste en una tarea asíncrona que retrasa un tiempo la obtención del contenido.
   * El modelo notificará al presentador cuando se inicia y cuando finaliza esta tarea.
   */
  private void startDelayedTask() {
//    Log.d(TAG, "calling startDelayedTask()");
//    runningTask = true;
//    getPresenter().onLoadItemsTaskStarted();
//
//    // Mock Hello: A handler to delay the answer
//    new Handler().postDelayed(new Runnable() {
//      @Override
//      public void run() {
//        setItems();
//        runningTask = false;
//        getPresenter().onLoadItemsTaskFinished(items);
//      }
//    }, 1000);
//  }

  }
}

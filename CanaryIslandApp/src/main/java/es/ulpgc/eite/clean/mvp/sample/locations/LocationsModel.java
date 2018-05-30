package es.ulpgc.eite.clean.mvp.sample.locations;

import android.os.Handler;
import android.util.Log;

import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.data.CategoryItem;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;
import es.ulpgc.eite.clean.mvp.sample.data.MasterDetailData;


public class LocationsModel
    extends GenericModel<Locations.ModelToPresenter> implements Locations.PresenterToModel {

  private String label;
  private List<LocationItem> items;
  public String language;
  private CategoryItem selecteditem;
  private String island;


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
  public void setItem(CategoryItem item) {
    selecteditem = item;
  }

  @Override
  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public void setIsland(String island) {
    this.island = island;
  }


  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Si el contenido ya ha sido fijado antes, se notificará inmediatamente al presentador y,
   * sino es el caso, la notificación se realizará al finalizar la tarea que fija este contenido
   */
  @Override
  public void loadItems() {

    //Call presenter to show progress
    getPresenter().onLoadItemsTaskStarted();

    //Recover the data from the database not asynchronously
    Log.d(TAG, "calling loadItemsFromJsonFile() method");
    if (MasterDetailData.getItemsFromDatabase().size() == 0) {
      MasterDetailData.loadItemsFromJsonFile
              (getPresenter().getManagedContext(), "locations.json");
    }
    Log.d(TAG, "calling onLoadItemsTaskFinished() method");
    items = MasterDetailData.getItemsFromDatabase();
    for (int i = 0; i < items.size(); i++) {
      //This if statement removes all the locations from the islands that were not selected
      if (!items.get(i).getDbItem().getIsland().getName().equals(island)) {
        items.remove(i);
        i = i - 1;
      } else {

        if (items.size() != 0) {
          //This if statement makes sure we only the elements from the category selected
          if (language.equals("Spanish")) {
            if (!items.get(i).getCategory(language).equals(selecteditem.getSpanishName())) {
              items.remove(i);
              i = i - 1;
            }
          }
          if (language.equals("English")) {
            if (!items.get(i).getCategory(language).equals(selecteditem.getEnglishName())) {
              items.remove(i);
              i = i - 1;
            }
          }
          if (language.equals("German")) {
            if (!items.get(i).getCategory(language).equals(selecteditem.getGermanName())) {
              items.remove(i);
              i = i - 1;
            }
          }
        }
      }
    }
    getPresenter().onLoadItemsTaskFinished(items);
  }

}


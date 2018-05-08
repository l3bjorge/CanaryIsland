package es.ulpgc.eite.clean.mvp.sample.category;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;


public class CategoryModel
    extends GenericModel<Category.ModelToPresenter> implements Category.PresenterToModel {

  private String label;
  private String language;
  public List<ModelItem> items = null;
  private boolean runningTask;
  private String errorMsg;


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


  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Si el contenido ya ha sido fijado antes, se notificará inmediatamente al presentador y,
   * sino es el caso, la notificación se realizará al finalizar la tarea que fija este contenido
   */
  @Override
  public void loadItems() {
    if(items == null && !runningTask) {
      startDelayedTask();
    } else {
      if(!runningTask){
        getPresenter().onLoadItemsTaskFinished(items);
      } else {
        getPresenter().onLoadItemsTaskStarted();
      }
    }
  }

  /**
   * Llamado para recuperar los elementos iniciales de la lista.
   * En este caso siempre se llamará a la tarea asíncrona
   */
  @Override
  public void reloadItems() {
    items = null;
    loadItems();
  }

  @Override
  public String getErrorMessage() {
    return errorMsg;
  }

  /////////////////////////////////////////////////////////////////////////////////////

  private void addItem(ModelItem item) {
    items.add(item);
  }

  private ModelItem createItem(int position) {
    return new ModelItem(String.valueOf(position), "Category ");
  }

  private void setItems(){
    items = new ArrayList();

    if (language == "English") {

      addItem(new ModelItem(String.valueOf(0), "Beaches"));
      addItem(new ModelItem(String.valueOf(1), "Parks"));
      addItem(new ModelItem(String.valueOf(2), "Shopping Centers"));
      addItem(new ModelItem(String.valueOf(3), "Museums"));
      addItem(new ModelItem(String.valueOf(4), "Restaurants"));
      addItem(new ModelItem(String.valueOf(5), "Touristic Spots"));
      addItem(new ModelItem(String.valueOf(6), "Night Clubs"));
      addItem(new ModelItem(String.valueOf(7), "Theaters"));
      addItem(new ModelItem(String.valueOf(8), "Cultural Spots"));
      addItem(new ModelItem(String.valueOf(9), "Activities"));

    } else
    if (language == "Spanish") {

      addItem(new ModelItem(String.valueOf(0), "Playas"));
      addItem(new ModelItem(String.valueOf(1), "Parques"));
      addItem(new ModelItem(String.valueOf(2), "Centros Comerciales"));
      addItem(new ModelItem(String.valueOf(3), "Museos"));
      addItem(new ModelItem(String.valueOf(4), "Restaurantes"));
      addItem(new ModelItem(String.valueOf(5), "Puntos Turísticos"));
      addItem(new ModelItem(String.valueOf(6), "Discotecas"));
      addItem(new ModelItem(String.valueOf(7), "Teatros"));
      addItem(new ModelItem(String.valueOf(8), "Puntos Culturales"));
      addItem(new ModelItem(String.valueOf(9), "Actividades"));

    }

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

    // Mock Hello: A handler to delay the answer
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        setItems();
        runningTask = false;
        getPresenter().onLoadItemsTaskFinished(items);
      }
    }, 1000);
  }

}

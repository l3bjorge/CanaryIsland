package es.ulpgc.eite.clean.mvp.sample.category;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;


public class CategoryModel
    extends GenericModel<Category.ModelToPresenter> implements Category.PresenterToModel {

  private static final int ITEM_COUNT = 9;

  private String label;
  private String buttonBeach;
  private String buttonRest;
  private String buttonTourist;
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
    buttonBeach = "Beaches";
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
    return new ModelItem(String.valueOf(position), "Category " + position, makeDetails(position));
  }

  private String makeDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Item: ").append(position).append("\n");
    for (int count = 0; count < position; count++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  private void setItems(){
    items = new ArrayList();

      addItem(new ModelItem(String.valueOf(0), "Beaches", makeDetails(0)));
      addItem(new ModelItem(String.valueOf(1), "Parks", makeDetails(1)));
      addItem(new ModelItem(String.valueOf(2), "Shopping Centers", makeDetails(2)));
      addItem(new ModelItem(String.valueOf(3), "Museums", makeDetails(3)));
      addItem(new ModelItem(String.valueOf(4), "Restaurants", makeDetails(4)));
      addItem(new ModelItem(String.valueOf(5), "Touristic Spots", makeDetails(5)));
      addItem(new ModelItem(String.valueOf(6), "Night Clubs", makeDetails(6)));
      addItem(new ModelItem(String.valueOf(7), "Theaters", makeDetails(7)));
      addItem(new ModelItem(String.valueOf(8), "Cultural Spots", makeDetails(8)));
      addItem(new ModelItem(String.valueOf(9), "Activities", makeDetails(9)));

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

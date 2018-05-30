package es.ulpgc.eite.clean.mvp.sample.locations;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.data.CategoryItem;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;

/**
 * Created by Luis on 12/11/16.
 */

public interface Locations {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setSelectedItem(CategoryItem item);
    void setLanguage(String language);
    void setIsland(String island);
  }

  interface ToLocations extends State {
    void onScreenStarted();
  }

  interface LocationsTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();



      void onScreenResumed();
    LocationItem getLocation();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void goToDescriptionScreen(LocationItem item);
    void onResumingContent();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideProgress();
    void showError(String msg);
    void showProgress();
    void setActionBarTitle(String title);
    void setRecyclerAdapterContent(List<LocationItem> items);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getLabel();

    void setIsland(String island);

    void loadItems();
    void setItem(CategoryItem item);
    void setLanguage(String language);
    void reloadItems();
    String getErrorMessage();
    void setDatabaseValidity(boolean valid);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    Context getManagedContext();
    void onLoadItemsTaskFinished(List<LocationItem> items);
    void onLoadItemsTaskStarted();
    String getLanguage();
  }

}

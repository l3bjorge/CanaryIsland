package es.ulpgc.eite.clean.mvp.sample.locations;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;

/**
 * Created by Luis on 12/11/16.
 */

public interface Locations {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setSelectedItem(ModelItem item);
  }

  interface ToLocations extends State {
    void onScreenStarted();
  }

  interface LocationsTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    void onScreenResumed();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void goToDescriptionScreen(ModelItem item);
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
    void setRecyclerAdapterContent(List<ModelItem> items);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getLabel();
    void loadItems();
    void setItem(ModelItem item);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    void onLoadItemsTaskFinished(List<ModelItem> items);
    void onLoadItemsTaskStarted();
    String getLanguage();
  }

}

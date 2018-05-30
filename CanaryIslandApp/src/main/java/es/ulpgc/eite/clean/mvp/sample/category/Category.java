package es.ulpgc.eite.clean.mvp.sample.category;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.data.CategoryItem;

/**
 * Created by Luis on 12/11/16.
 */

public interface Category {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setLanguage(String language);
  }

  interface ToCategory extends State {
    void onScreenStarted();
  }

  interface CategoryTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    void onScreenResumed();
    CategoryItem getSelectedItem();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void goToLocationsScreen(CategoryItem item);
    void onResumingContent();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideProgress();
    void showProgress();
    void setRecyclerAdapterContent(List<CategoryItem> items, String language);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getLabel();
    void setLanguage(String language);
    void loadItemsFromDatabase();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    void onLoadItemsTaskFinished(List<CategoryItem> items, String language);
    String getLanguage();
    Context getManagedContext();

  }

}

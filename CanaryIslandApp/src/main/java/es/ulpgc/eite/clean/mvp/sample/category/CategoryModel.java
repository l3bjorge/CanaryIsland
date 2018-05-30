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

  }


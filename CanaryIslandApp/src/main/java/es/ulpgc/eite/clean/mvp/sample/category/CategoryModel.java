package es.ulpgc.eite.clean.mvp.sample.category;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenu;


public class CategoryModel
    extends GenericModel<Category.ModelToPresenter> implements Category.PresenterToModel {

  private String msgText;
  private String buttonBeach;
  private String buttonRest;
  private String buttonTourist;


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
    msgText = "Choose your island";
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
  public String getText() {
    return msgText;
  }

  public String getButtonBeach() {
    return buttonBeach;
  }


  public String getButtonRest() {
    return buttonRest;
  }


  public String getButtonTourist() {
    return buttonTourist;
  }

}

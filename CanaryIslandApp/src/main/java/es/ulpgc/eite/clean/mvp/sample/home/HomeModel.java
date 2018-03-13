package es.ulpgc.eite.clean.mvp.sample.home;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class HomeModel
    extends GenericModel<Home.ModelToPresenter> implements Home.PresenterToModel {


  private String englishLabel;
  private String msgText;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Home.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");
    msgText = "Canary Island";
    englishLabel = "English";
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

  @Override
  public String getLabel() {
    return englishLabel;
  }


}

package es.ulpgc.eite.clean.mvp.sample.locations;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class LocationsModel
    extends GenericModel<Locations.ModelToPresenter> implements Locations.PresenterToModel {



  private String label;
  private String buttoncanteras;



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

    label = "choose your location";
    buttoncanteras = "Las Canteras";
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
  public String getButtonCanteras() {
    return buttoncanteras;
  }

  @Override
  public String getLabel() {
    return label;
  }

}

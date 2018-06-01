package es.ulpgc.eite.clean.mvp.sample.home;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class HomeModel
    extends GenericModel<Home.ModelToPresenter> implements Home.PresenterToModel {

  private String label, textEspa, textEngl, textDeut;


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
    label = "Canary Islands";
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


  /**
   * Method to get the value of the "label" parameter that it the title of our app
   * @return the value of the "label" parameter with the title of the app
   */
  @Override
  public String getLabel() {
    return label;
  }

  /**
   *  Method to get the value of the "label" parameter that it the spanish label of our app
   * @return the value of the "label" parameter with the spanish label of the app
   */
  public String getTextEspa(){
    textEspa = "Español";
    return textEspa;
  }

  /**
   *  Method to get the value of the "label" parameter that it the spanish label of our app
   * @return the value of the "label" parameter with the spanish label of the app
   */
  public String getTextEngl(){
    textEngl = "English";
    return textEngl;
  }

  /**
   *  Method to get the value of the "label" parameter that it the spanish label of our app
   * @return the value of the "label" parameter with the spanish label of the app
   */
  public String getTextDeut(){
    textDeut = "Deutsch";
    return textDeut;
  }


}

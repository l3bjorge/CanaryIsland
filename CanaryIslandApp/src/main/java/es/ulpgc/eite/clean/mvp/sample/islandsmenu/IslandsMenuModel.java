package es.ulpgc.eite.clean.mvp.sample.islandsmenu;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class IslandsMenuModel
    extends GenericModel<IslandsMenu.ModelToPresenter> implements IslandsMenu.PresenterToModel {

  private String tittle, language;



  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(IslandsMenu.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");


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
  public String getTittle() {
    setLanguage(getPresenter().getLanguage());
    if(language == "Spanish"){
      tittle = "Escoge tu isla";
    } else if (language == "English"){
      tittle = "Choose your island";
    } else if (language == "German"){
      tittle = "Wähle deine Insel";
    }
    return tittle;
  }

  @Override
  public void setLanguage(String language) {
    this.language = language;
  }



}

package es.ulpgc.eite.clean.mvp.sample.islandsmenu;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class IslandsMenuModel
    extends GenericModel<IslandsMenu.ModelToPresenter> implements IslandsMenu.PresenterToModel {

  private String tittle, language, textFtv, textLanz, textGranca, textTnf, textHierr, textPalma, textGome;



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


  /**
   * Method to get the value of the "label" parameter that it the title of our app, according to the language
   * @return the value of the "label" parameter with the title of the app
   */
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

  /**
   * Method that set the language selected
   * @param language
   */
  @Override
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   *  Method to get the value of the "label" parameter that it the Fuerteventura label of our app
   * @return the value of the "label" parameter with the Fuerteventura label of the app
   */
  @Override
  public String getTextFtv(){
    textFtv = "Fuerteventura";
    return textFtv;
  }

  /**
   *  Method to get the value of the "label" parameter that it the Lanzarote label of our app
   * @return the value of the "label" parameter with the Lanzarote label of the app
   */
  @Override
  public String getTextLanz(){
    textFtv = "Lanzarote";
    return textFtv;
  }

  /**
   *  Method to get the value of the "label" parameter that it the Gran Canaria label of our app
   * @return the value of the "label" parameter with the Gran Canaria label of the app
   */
  @Override
  public String getTextGranca(){
    textGranca = "Gran Canaria";
    return textGranca;
  }

  /**
   *  Method to get the value of the "label" parameter that it the Tenerife label of our app
   * @return the value of the "label" parameter with the Tenerife label of the app
   */
  @Override
  public String getTextTnf(){
    textTnf = "Tenerife";
    return textTnf;
  }

  /**
   *  Method to get the value of the "label" parameter that it the El Hierro label of our app
   * @return the value of the "label" parameter with the El Hierro label of the app
   */
  @Override
  public String getTextHierr(){
    textHierr = "El Hierro";
    return textHierr;
  }

  /**
   *  Method to get the value of the "label" parameter that it the La Palma label of our app
   * @return the value of the "label" parameter with the La Palma label of the app
   */
  @Override
  public String getTextPalma(){
    textPalma = "La Palma";
    return textPalma;
  }

  /**
   *  Method to get the value of the "label" parameter that it the La Gomera label of our app
   * @return the value of the "label" parameter with the La Gomera label of the app
   */
  @Override
  public String getTextGome(){
    textGome = "La Gomera";
    return textGome;
  }

}

package es.ulpgc.eite.clean.mvp.sample.description;


import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class DescriptionModel
        extends GenericModel<Description.ModelToPresenter> implements Description.PresenterToModel {


  private String tittleText, descriptionText, location, web, facebook, idFacebook, instagram, idInstagram;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Description.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");

//    location = "https://www.google.es/maps/place/Playa+De+Las+Canteras/@28.1383998,-15.4380441,16.5z/data=!4m5!3m4!1s0xc4095163c74eccb:0x3e52f017b6b424c4!8m2!3d28.1372993!4d-15.43791?hl=es&authuser=0";
//    web = /*null;*/ "http://miplayadelascanteras.com/";
//    facebook = /*null;*/ "https://es-es.facebook.com/periodicoCanarias7/";
//    idFacebook = "fb://page/172572586147087";
//    instagram = /*null;*/ "https://www.instagram.com/instagram/?hl=es";
//    idInstagram = "http://instagram.com/_u/instagram";

    location = "";
    web = "";
    facebook = "";
    idFacebook = "";
    instagram = "";
    idInstagram = "";


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
   *Method that get the tittle of the location
   * @return tittle of the location
   */
  @Override
  public String getTittle() {
    return tittleText;
  }

  /**
   *Method that get the description of the location
   * @return description of the location
   */
  @Override
  public String getDescription() {
    return descriptionText;
  }

  /**
   *Method that get the location in googleMaps
   * @return location of the location
   */
  @Override
  public String getLocation() {
    return location;
  }

  /**
   *Method that get the url of the location
   * @return url of the location
   */
  @Override
  public String getWeb() {
    return web;
  }

  /**
   *Method that get the facebook page of the location to open it in web browser
   * @return url facebook of the location
   */
  @Override
  public String getFacebook() {
    return facebook;
  }

  /**
   *Method that get the instagram page of the location to open it in web browser
   * @return url instagram of the location
   */
  @Override
  public String getInstagram() {
    return instagram;
  }

  /**
   * Method that get the facebook id of the location to open it in the facebook app
   * @return id instagram of the location
   */
  @Override
  public String getIdInstagram() {
    return idInstagram;
  }


  /**
   *Method that get the instagram id of the location to open it in the instagram app
   * @return id facebook of the location
   */
  @Override
  public String getIdFacebook() {
    return idFacebook;
  }

  /**
   * Method that set the location for googleMaps
   * @param location Value that is assigned to the location variable
   */
  @Override
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Method that set the web of the location
   * @param web Value that is assigned to the web variable
   */
  @Override
  public void setWeb(String web) {
    this.web = web;
  }

  /**
   * Method that set the facebook page of the location to open in web browser
   * @param facebook Value that is assigned to the facebook variable
   */
  @Override
  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  /**
   * Method that set the facebook id of the location to open in facebook app
   * @param idFacebook Value that is assigned to the idFacebook variable
   */
  @Override
  public void setIdFacebook(String idFacebook) {
    this.idFacebook = idFacebook;
  }

  /**
   * Method that set the instagram page of the location to open in web browser
   * @param instagram Value that is assigned to the instagram variable
   */
  @Override
  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  /**
   * Method that set the facebook page of the location to open in web browser
   * @param idInstagram Value that is assigned to the idInstagram variable
   */
  @Override
  public void setIdInstagram(String idInstagram) {
    this.idInstagram = idInstagram;
  }



}

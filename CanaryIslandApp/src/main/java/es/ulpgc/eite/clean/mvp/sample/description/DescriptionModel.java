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
   *
   * @return tittle of the location
   */
  @Override
  public String getTittle() {
    return tittleText;
  }

  /**
   *
   * @return description of the location
   */
  @Override
  public String getDescription() {
    return descriptionText;
  }

  /**
   *
   * @return location of the location
   */
  @Override
  public String getLocation() {
    return location;
  }

  /**
   *
   * @return url of the location
   */
  @Override
  public String getWeb() {
    return web;
  }

  /**
   *
   * @return url facebook of the location
   */
  @Override
  public String getFacebook() {
    return facebook;
  }

  /**
   *
   * @return url instagram of the location
   */
  @Override
  public String getInstagram() {
    return instagram;
  }

  @Override
  public String getIdInstagram() {
    return idInstagram;
  }


  /**
   *
   * @return id facebook of the location
   */
  @Override
  public String getIdFacebook() {
    return idFacebook;
  }


}

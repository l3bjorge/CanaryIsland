package es.ulpgc.eite.clean.mvp.sample.description;

import android.net.Uri;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class DescriptionModel
    extends GenericModel<Description.ModelToPresenter> implements Description.PresenterToModel {


  private String tittleText, descriptionText, location, web, facebook, idFacebook, instagram;



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


    tittleText = "Las Canteras";
    descriptionText = "The beach of Las Canteras is the main urban beach of the city of Las Palmas de Gran Canaria (Gran Canaria, Canary Islands). Frequented throughout the year, it is the beach preferred by most of the city's inhabitants and foreigners who visit it, who can enjoy it at any time of the year thanks to the mild climate.";
    location = "https://www.google.es/maps/place/Playa+De+Las+Canteras/@28.1383998,-15.4380441,16.5z/data=!4m5!3m4!1s0xc4095163c74eccb:0x3e52f017b6b424c4!8m2!3d28.1372993!4d-15.43791?hl=es&authuser=0";
    web = /*null;*/ "http://miplayadelascanteras.com/";
    facebook = /*null;*/ "https://es-es.facebook.com/periodicoCanarias7/";
    idFacebook = "fb://page/172572586147087";
    instagram = /*null;*/ "https://www.instagram.com/instagram/?hl=es";

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
    return tittleText;
  }

  @Override
  public String getDescription() {
    return descriptionText;
  }

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public String getWeb() {
    return web;
  }

  @Override
  public String getFacebook() {
    return facebook;
  }

  @Override
  public String getInstagram() {
    return instagram;
  }

  @Override
  public String getIdFacebook() {
    return idFacebook;
  }


}

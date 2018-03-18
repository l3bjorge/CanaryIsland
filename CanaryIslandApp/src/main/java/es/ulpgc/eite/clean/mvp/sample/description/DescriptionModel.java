package es.ulpgc.eite.clean.mvp.sample.description;

import android.util.Log;

import es.ulpgc.eite.clean.mvp.GenericModel;


public class DescriptionModel
    extends GenericModel<Description.ModelToPresenter> implements Description.PresenterToModel {


  private String tittleText, descriptionText;
  private int numOfTimes;
  private int maxNumOfTimes;
  private String msgText;


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
  public boolean isNumOfTimesCompleted() {
    if(numOfTimes == maxNumOfTimes) {
      return true;
    }
    return false;
  }

  @Override
  public void changeMsgByBtnClicked() {
    msgText = tittleText;
    if(numOfTimes > 0) {
      msgText += ", " + (numOfTimes + 1) + " times";
    }
    numOfTimes++;
  }

  @Override
  public String getText() {
    return msgText;
  }


  @Override
  public void resetMsgByBtnClicked() {
    numOfTimes = 1;
    msgText = tittleText;
  }

}

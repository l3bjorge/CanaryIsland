package es.ulpgc.eite.clean.mvp.sample.description;

import android.content.Context;
import android.net.Uri;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Description {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setTittleVisibility(boolean visible);
    void setDescriptionVisibility(boolean visible);
  }

  interface ToDescription extends State {
    void onScreenStarted();
    //void setTextVisibility(boolean textVisibility);
    
  }

  interface DescriptionTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    boolean isTittleVisible();
    boolean isDescriptionVisible();
    boolean isLocationBttnVisible();
    boolean isWebBttnVisible();
    boolean isFaceBttnVisible();
    boolean isInstaBttnVisible();
    void onScreenResumed();

    //void setTextVisibility(boolean textVisibility);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onButtonClicked();
    void onBtnLocationCliked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void hideTittle();
    void showTittle();
    void setTittle(String txt);
    void hideDescription();
    void showDescription();
    void setDescription(String txt);
    void hideLocationBttn();
    void showLocationBttn();
    void hideWebBttn();
    void showWebBttn();
    void hideFaceBttn();
    void showFaceBttn();
    void hideInstaBttn();
    void showInstaBttn();


  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getTittle();
    String getDescription();
    String getLocation();
    String getWeb();
    String getFacebook();
    String getInstagram();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }

}

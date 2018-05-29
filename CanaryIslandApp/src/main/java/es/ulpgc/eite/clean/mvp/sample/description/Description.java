package es.ulpgc.eite.clean.mvp.sample.description;

import android.content.Context;
import android.net.Uri;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;

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
    void setLocationBttnVisibility(boolean visible);
    void setWebBttnVisiblity(boolean visible);
    void setFaceBttnVisiblity(boolean visible);
    void setInstaBttnVisiblity(boolean visible);
    void setSelectedItem(LocationItem selectedItem);

  }

  interface ToDescription extends State {
    void onScreenStarted();

    
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


  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onBtnLocationCliked();
    void onBtnWebCliked();
    void onBtnFacebookClicked();
    void onBtnInstagramClicked();
    void onBtnLikeCliked();

      void onBtnShareClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void displayShortMessage(String text);

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
    String getIdInstagram();
    String getIdFacebook();

    void setLocation(String location);
    void setWeb(String web);
    void setFacebook(String facebook);
    void setIdFacebook(String idFacebook);
    void setInstagram(String instagram);
    void setIdInstagram(String idInstagram);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {
    String getLanguage();
  }

}

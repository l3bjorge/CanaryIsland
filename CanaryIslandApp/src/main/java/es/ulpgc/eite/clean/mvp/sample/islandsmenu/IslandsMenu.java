package es.ulpgc.eite.clean.mvp.sample.islandsmenu;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface IslandsMenu {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setLanguage(String language);
  }

  interface ToIslandsMenu extends State {
    void onScreenStarted();

  }

  interface IslandsMenuTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    void onScreenResumed();
    String checkIsland();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onGcButtonClicked();
    void onTnfButtonClicked();
    void onLnzButtonClicked();
    void onFtvButtonClicked();
    void onPlmButtonClicked();
    void onGmrButtonClicked();
    void onHrrButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void setTextaso(String textaso, String textasoFtv, String textasoLanz, String textasoGranca, String textasoTnf);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getTittle(); // El presentador pide al modelo que le de el textaso
    void setLanguage(String language);


    String getTextFtv();
    String getTextLanz();
    String getTextGranca();
    String getTextTnf();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

    String getLanguage();


  }


}

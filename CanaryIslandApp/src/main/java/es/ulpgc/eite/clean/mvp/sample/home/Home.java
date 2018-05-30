package es.ulpgc.eite.clean.mvp.sample.home;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Home {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
  }

  interface ToHome extends State {
    void onScreenStarted();
  }

  interface HomeTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    String checkLanguage();
    void onScreenResumed();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onEnglishButtonClicked();
    void onSpanishButtonClicked();
    void onGermanButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void setLabel(String txt, String textoEspa, String textoEngl, String textoDeut);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    String getLabel();

    String getTextEspa();
    String getTextEngl();
    String getTextDeut();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }

}

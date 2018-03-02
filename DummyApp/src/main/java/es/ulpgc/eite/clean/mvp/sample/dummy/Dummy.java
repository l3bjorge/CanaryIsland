package es.ulpgc.eite.clean.mvp.sample.dummy;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;

/**
 * Created by Luis on 12/11/16.
 */

public interface Dummy {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
    void setToolbarVisibility(boolean visible);
    void setTextVisibility(boolean visible);
  }

  interface ToDummy extends State {
    void onScreenStarted();
  }

  interface DummyTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isToolbarVisible();
    boolean isTextVisible();
    void onScreenResumed();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
    void hideText();
    void showText();
    void setText(String txt);
    void setLabel(String txt);
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    boolean isNumOfTimesCompleted();
    void changeMsgByBtnClicked();
    String getText();
    String getLabel();
    void resetMsgByBtnClicked();
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }

}

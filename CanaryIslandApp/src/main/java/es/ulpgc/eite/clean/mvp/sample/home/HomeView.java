package es.ulpgc.eite.clean.mvp.sample.home;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class HomeView
    extends GenericActivity<Home.PresenterToView, Home.ViewToPresenter, HomePresenter>
    implements Home.PresenterToView {

  private Toolbar toolbar;
  private ImageButton buttonEng, buttonSpa, buttonGer;
  private TextView text, textEspa, textEngl, textDeut;


  /**
   * Method that states what to do when we create the screen.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);
    textEspa = (TextView) findViewById(R.id.textEspa);
    textEngl = (TextView) findViewById(R.id.textEngl);
    textDeut = (TextView) findViewById(R.id.textDeut);


    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    buttonEng = (ImageButton) findViewById(R.id.ButtonEnglish);
    buttonEng.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onEnglishButtonClicked();
      }
    });

    buttonSpa = (ImageButton) findViewById(R.id.ButtonSpain);
    buttonSpa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onSpanishButtonClicked();
      }
    });

    buttonGer = (ImageButton) findViewById(R.id.ButtonGermany);
    buttonGer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onGermanButtonClicked();
      }
    });
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(HomePresenter.class, this);
  }

  /**
   * Method that states what to do when we press the button back.
   */
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Method that states what to do when we destroy the screen.
   */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  /**
   *Method that finish this screen.
   */
  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  /**
   *Method that hide the toolbar
   */
  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
  }

  /**
   * Method that set the tittle and the languages labels
   * @param title this is the text of tittle label
   * @param TextoEspa this is the text of spanish label
   * @param TextoEngl this is the text of english label
   * @param TextoDeut this is the text of german label
   */
  @Override
  public void setLabel(String title, String TextoEspa, String TextoEngl, String TextoDeut) {
    text.setText(title);
    textEspa.setText(TextoEspa);
    textEngl.setText(TextoEngl);
    textDeut.setText(TextoDeut);

  }
}

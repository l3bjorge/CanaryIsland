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
  private TextView text;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);


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
   * Method that states what to do when we press the button back
   */
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  /**
   * Method that states what to do when we destroy the screen
   */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  /**
   *
   */
  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  /**
   *
   */
  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
  }

  /**
   *
   * @param title
   */
  @Override
  public void setLabel(String title) {
    text.setText(title);
  }
}

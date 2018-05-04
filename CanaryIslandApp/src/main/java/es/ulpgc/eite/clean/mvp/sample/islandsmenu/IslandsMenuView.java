package es.ulpgc.eite.clean.mvp.sample.islandsmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class IslandsMenuView
    extends GenericActivity<IslandsMenu.PresenterToView, IslandsMenu.ViewToPresenter, IslandsMenuPresenter>
    implements IslandsMenu.PresenterToView {

  private Toolbar toolbar;
  private ImageButton button;
  private ImageButton buttonTnf;
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_islandsmenu);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    button = (ImageButton) findViewById(R.id.ButtonGranCa);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().goToCategoryScreen();
      }
    });

    button = (ImageButton) findViewById(R.id.ButtonTenerife);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().goToCategoryScreen();
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
    super.onResume(IslandsMenuPresenter.class, this);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
  }

  @Override
  public void setTextaso(String textaso) {
    text.setText(textaso);
  }
}

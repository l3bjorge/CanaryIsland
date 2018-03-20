package es.ulpgc.eite.clean.mvp.sample.description;

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

public class DescriptionView
    extends GenericActivity<Description.PresenterToView, Description.ViewToPresenter, DescriptionPresenter>
    implements Description.PresenterToView {

  private Toolbar toolbar;
  private ImageButton likeBttn, locationBttn;
  private TextView tittle, description;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);
    Log.d(TAG, "calling onCreate()");

    tittle = (TextView) findViewById(R.id.tittle);
    description = (TextView) findViewById(R.id.description);


    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    likeBttn = (ImageButton) findViewById(R.id.likeBttn);
    likeBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnLocationCliked();
      }
    });

    locationBttn = (ImageButton) findViewById(R.id.locationBttn);
    locationBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

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
    super.onResume(DescriptionPresenter.class, this);
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
  public void hideTittle() {
    tittle.setVisibility(View.GONE);
  }

  @Override
  public void showTittle() {
    tittle.setVisibility(View.VISIBLE);
  }

  @Override
  public void setTittle(String txt) {
    tittle.setText(txt);
  }

  @Override
  public void hideDescription() {

  }

  @Override
  public void showDescription() {

  }

  @Override
  public void setDescription(String txt) {

  }


}

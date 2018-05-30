package es.ulpgc.eite.clean.mvp.sample.description;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class DescriptionView
        extends GenericActivity<Description.PresenterToView, Description.ViewToPresenter, DescriptionPresenter>
        implements Description.PresenterToView {

  private Toolbar toolbar;
  private ImageButton likeBttn, locationBtn, webBtn, faceBtn, instaBtn, shareBtn;
  private TextView tittle, description;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_description);
    Log.d(TAG, "calling onCreate()");

    tittle = (TextView) findViewById(R.id.tittle);
    description = (TextView) findViewById(R.id.description);


    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    likeBttn = (ImageButton) findViewById(R.id.likeBtn);
    likeBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnLikeCliked();
      }
    });

    shareBtn = (ImageButton) findViewById(R.id.shareBtn);
    shareBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnShareClicked();
      }
    });

    locationBtn = (ImageButton) findViewById(R.id.locationBtn);
    locationBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnLocationCliked();
      }
    });

    webBtn = (ImageButton) findViewById(R.id.webBtn);
    webBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnWebCliked();
      }
    });

    faceBtn = (ImageButton) findViewById(R.id.faceBtn);
    faceBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnFacebookClicked();
      }
    });

    instaBtn = (ImageButton) findViewById(R.id.instaBtn);
    instaBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onBtnInstagramClicked();
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


  @Override
  public void displayShortMessage(String text) {
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
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
    tittle.setVisibility(View.INVISIBLE);
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
    description.setVisibility(View.INVISIBLE);
  }

  @Override
  public void showDescription() {
    description.setVisibility(View.VISIBLE);
  }

  @Override
  public void setDescription(String txt) {
    description.setText(txt);
  }

  @Override
  public void hideLocationBttn() {
    locationBtn.setVisibility(View.INVISIBLE);
  }

  @Override
  public void showLocationBttn() {
    locationBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideWebBttn() {
    webBtn.setVisibility(View.GONE);
  }

  @Override
  public void showWebBttn() {
    webBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideFaceBttn() {
    faceBtn.setVisibility(View.GONE);
  }

  @Override
  public void showFaceBttn() {
    faceBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideInstaBttn() {
    instaBtn.setVisibility(View.GONE);
  }

  @Override
  public void showInstaBttn() {
    instaBtn.setVisibility(View.VISIBLE);
  }

}

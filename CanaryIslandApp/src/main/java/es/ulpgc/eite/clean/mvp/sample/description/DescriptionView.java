package es.ulpgc.eite.clean.mvp.sample.description;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class DescriptionView
        extends GenericActivity<Description.PresenterToView, Description.ViewToPresenter, DescriptionPresenter>
        implements Description.PresenterToView {

  private Toolbar toolbar;
  private ImageButton likeBtn, locationBtn, webBtn, faceBtn, instaBtn, shareBtn;
  private TextView tittle, description;
  private ImageView image;
  private String url;/*"https://www.surfsearchspot.com/wp-content/uploads/2016/06/las-canteras-mejor-playa-urbana-del-mundo.png";*/

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

    likeBtn = (ImageButton) findViewById(R.id.likeBtn);
    likeBtn.setOnClickListener(new View.OnClickListener() {
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

    image =(ImageView)findViewById(R.id.photo);
    loadImageFromUrl(url);
  }


  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(DescriptionPresenter.class, this);
    image =(ImageView)findViewById(R.id.photo);
    loadImageFromUrl(url);
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
  public void hideLocationBtn() {
    locationBtn.setVisibility(View.GONE);
  }

  @Override
  public void showLocationBtn() {
    locationBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideWebBtn() {
    webBtn.setVisibility(View.GONE);
  }

  @Override
  public void showWebBtn() {
    webBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideFaceBtn() {
    faceBtn.setVisibility(View.GONE);
  }

  @Override
  public void showFaceBtn() {
    faceBtn.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideInstaBtn() {
    instaBtn.setVisibility(View.GONE);
  }

  @Override
  public void showInstaBtn() {
    instaBtn.setVisibility(View.VISIBLE);
  }

  @Override

  public void setImage(String url){
    this.url = url;
  }
  private void loadImageFromUrl(String url) {

    Picasso.with(this).load(url)
            .into(image,new com.squareup.picasso.Callback(){


              @Override
              public void onSuccess() {

              }

              @Override
              public void onError() {

              }
            });

  }
}

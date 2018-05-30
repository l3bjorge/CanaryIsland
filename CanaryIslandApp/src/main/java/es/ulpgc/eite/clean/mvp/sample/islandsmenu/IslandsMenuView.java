package es.ulpgc.eite.clean.mvp.sample.islandsmenu;

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

public class IslandsMenuView
    extends GenericActivity<IslandsMenu.PresenterToView, IslandsMenu.ViewToPresenter, IslandsMenuPresenter>
    implements IslandsMenu.PresenterToView {

  private Toolbar toolbar;
  private ImageButton buttonGc;
  private ImageButton buttonTnf;
  private ImageButton buttonFtv;
  private ImageButton buttonLanz;
  private ImageButton buttonPalm;
  private ImageButton buttonHierr;
  private ImageButton buttonGom;

  private TextView text, textFtv, textLanz, textGranca, textTnf, textHierr, textPalma, textGome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_islandsmenu);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);

    textFtv = (TextView) findViewById(R.id.textFtv);
    textLanz = (TextView) findViewById(R.id.textLanz);
    textGranca = (TextView) findViewById(R.id.textGranca);
    textTnf = (TextView) findViewById(R.id.textTnf);
    textHierr = (TextView) findViewById(R.id.textHierr);
    textPalma = (TextView) findViewById(R.id.textPalma);
    textGome = (TextView) findViewById(R.id.textGome);



    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    buttonGc = (ImageButton) findViewById(R.id.ButtonGranCa);
    buttonGc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onGcButtonClicked();
      }
    });

    buttonTnf = (ImageButton) findViewById(R.id.ButtonTenerife);
    buttonTnf.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onTnfButtonClicked();
      }
    });

    buttonFtv = (ImageButton) findViewById(R.id.ButtonFuerteventura);
    buttonFtv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onFtvButtonClicked();
      }
    });

    buttonLanz = (ImageButton) findViewById(R.id.ButtonLanzarote);
    buttonLanz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onLnzButtonClicked();
      }
    });

    buttonPalm = (ImageButton) findViewById(R.id.ButtonPalma);
    buttonPalm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onPlmButtonClicked();
      }
    });

    buttonHierr = (ImageButton) findViewById(R.id.ButtonHierro);
    buttonHierr.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onHrrButtonClicked();
      }
    });

    buttonGom = (ImageButton) findViewById(R.id.ButtonGomera);
    buttonGom.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onGmrButtonClicked();
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
  public void setTextaso(String textaso, String textasoFtv, String textasoLanz, String textasoGranca, String textasoTnf, String textasoHierr, String textasoPalma, String textasoGome) {
    text.setText(textaso);
    textFtv.setText(textasoFtv);
    textLanz.setText(textasoLanz);
    textGranca.setText(textasoGranca);
    textTnf.setText(textasoTnf);
    textHierr.setText(textasoHierr);
    textPalma.setText(textasoPalma);
    textGome.setText(textasoGome);
  }

}

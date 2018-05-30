package es.ulpgc.eite.clean.mvp.sample.locations;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class LocationsView
    extends GenericActivity<Locations.PresenterToView, Locations.ViewToPresenter, LocationsPresenter>
    implements Locations.PresenterToView {

  private Toolbar toolbar;
  private ProgressBar progressView;
  private RecyclerView recyclerView;
  private ActionBar actionbar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_locations);
    Log.d(TAG, "calling onCreate()");

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    actionbar = getSupportActionBar();


    progressView = (ProgressBar) findViewById(R.id.progress_circle);
    recyclerView = (RecyclerView) findViewById(R.id.item_list);
    recyclerView = (RecyclerView) findViewById(R.id.item_list);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
            VERTICAL);
    recyclerView.addItemDecoration(dividerItemDecoration);
    recyclerView.setAdapter(new LocationsView.ModelItemRecyclerViewAdapter());
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(LocationsPresenter.class, this);
    Log.d(TAG, "calling onResume()");

    getPresenter().onResumingContent();
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
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_master, menu);
    return true;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  @Override
  public void hideProgress() {
    progressView.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showError(String msg) {
    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
  }

  @Override
  public void showProgress() {
    progressView.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
  }


  @Override
  public void setActionBarTitle(String title) {
    if (actionbar != null) {
      actionbar.setTitle(title);
    }
  }

  @Override
  public void setRecyclerAdapterContent(List<LocationItem> items) {
    Log.d(TAG, "calling setRecyclerAdapterContent()");

    if (recyclerView != null) {
      LocationsView.ModelItemRecyclerViewAdapter recyclerAdapter =
              (LocationsView.ModelItemRecyclerViewAdapter) recyclerView.getAdapter();
      recyclerAdapter.setItemList(items);
    }
  }


/////////////////////////////////////////////////////////////////////////////////////


  private class ModelItemRecyclerViewAdapter
          extends RecyclerView.Adapter<LocationsView.ModelItemRecyclerViewAdapter.ViewHolder> {

    private List<LocationItem> items;

    public ModelItemRecyclerViewAdapter() {
      items = new ArrayList<>();
    }

    public void setItemList(List<LocationItem> items) {
      this.items = items;
      notifyDataSetChanged();
    }


    @Override
    public LocationsView.ModelItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_list_content, parent, false);
      return new LocationsView.ModelItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LocationsView.ModelItemRecyclerViewAdapter.ViewHolder holder, int position) {
      holder.item = items.get(position);
      holder.contentView.setText(items.get(position).getTitle());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          getPresenter().goToDescriptionScreen(holder.item);
        }
      });
    }

    @Override
    public int getItemCount() {
      return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public final View itemView;
      public final TextView contentView;
      public LocationItem item;

      public ViewHolder(View view) {
        super(view);
        itemView = view;
        contentView = (TextView) view.findViewById(R.id.item_content);
      }

      @Override
      public String toString() {
        return super.toString() + " '" + contentView.getText() + "'";
      }
    }
  }
}
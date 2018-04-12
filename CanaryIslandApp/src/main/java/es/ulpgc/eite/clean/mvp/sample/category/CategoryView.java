package es.ulpgc.eite.clean.mvp.sample.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.app.ModelItem;

public class CategoryView
    extends GenericActivity<Category.PresenterToView, Category.ViewToPresenter, CategoryPresenter>
    implements Category.PresenterToView {

  private Toolbar toolbar;
  private Button button;
  private TextView text;
  private ProgressBar progressView;
  private RecyclerView recyclerView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    Log.d(TAG, "calling onCreate()");

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionbar = getSupportActionBar();
    if (actionbar != null) {
      actionbar.setTitle(getString(R.string.title_item_list));
    }

    progressView = (ProgressBar) findViewById(R.id.progress_circle);
    recyclerView = (RecyclerView) findViewById(R.id.item_list);
    recyclerView.setAdapter(new ModelItemRecyclerViewAdapter());
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(CategoryPresenter.class, this);
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


  /*@Override
  public void setLabel(String txt) {
    text.setText(txt);
  }*/

  @Override
  public void setRecyclerAdapterContent(List<ModelItem> items) {
    Log.d(TAG, "calling setRecyclerAdapterContent()");

    if (recyclerView != null) {
      ModelItemRecyclerViewAdapter recyclerAdapter =
              (ModelItemRecyclerViewAdapter) recyclerView.getAdapter();
      recyclerAdapter.setItemList(items);
    }
  }


/////////////////////////////////////////////////////////////////////////////////////


  private class ModelItemRecyclerViewAdapter
          extends RecyclerView.Adapter<ModelItemRecyclerViewAdapter.ViewHolder> {

    private List<ModelItem> items;

    public ModelItemRecyclerViewAdapter() {
      items = new ArrayList<>();
    }

    public void setItemList(List<ModelItem> items) {
      this.items = items;
      notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_list_content, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.item = items.get(position);
      holder.contentView.setText(items.get(position).getContent());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          getPresenter().goToLocationsScreen(holder.item);
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
      public ModelItem item;

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
package com.prolificinteractive.configurations.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.prolificinteractive.configurations.R;
import com.prolificinteractive.configurations.binding.configurations.EmptyViewConfiguration;
import com.prolificinteractive.configurations.binding.configurations.ProgressWithTextConfiguration;
import com.prolificinteractive.configurations.binding.configurations.RecyclerViewConfiguration;
import com.prolificinteractive.configurations.binding.configurations.SnackbarConfiguration;
import com.prolificinteractive.configurations.binding.configurations.ToolbarConfiguration;
import com.prolificinteractive.configurations.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  // Configurations
  private ProgressWithTextConfiguration progress = new ProgressWithTextConfiguration();
  private SnackbarConfiguration snackbar = new SnackbarConfiguration();
  private EmptyViewConfiguration emptyView = new EmptyViewConfiguration();
  private ToolbarConfiguration toolbar = new ToolbarConfiguration();
  private RecyclerViewConfiguration recyclerView = new RecyclerViewConfiguration();

  private MyAdapter adapter = new MyAdapter();
  private AlertDialog alert;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    setSupportActionBar(binding.toolbarLayout.toolbar);

    // Mock list
    final ArrayList<String> items = new ArrayList<>();
    items.add("Check");
    items.add("The");
    items.add("Options");
    items.add("In");
    items.add("The");
    items.add("Menu");
    items.add("Of");
    items.add("The");
    items.add("Toolbar");
    items.add("!!");
    adapter.swapData(items);

    binding.setRecyclerView(recyclerView);
    binding.setSnackbar(snackbar);
    binding.setEmptyView(emptyView);
    binding.setProgress(progress);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    final MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_options, menu);
    return true;
  }

  @Override protected void onResume() {
    super.onResume();

    recyclerView.setConfig(new LinearLayoutManager(this), adapter);
    toolbar.setConfig(getString(R.string.app_name), v -> terminate());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.hello:
        hello();
        return true;
      case R.id.load:
        load();
        return true;
      case R.id.list:
        list();
        return true;
      case R.id.error:
        error();
        return true;
      case R.id.empty:
        empty();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void hello() {
    snackbar.newState("Hello!")
        .setType(SnackbarConfiguration.Type.VALID)
        .setDuration(Snackbar.LENGTH_LONG)
        .commit();
  }

  private void load() {
    progress.setLoading(true);
  }

  private void list() {
    recyclerView.show();
    progress.setLoading(false);
    emptyView.hide();
  }

  private void error() {
    emptyView.newState()
        .setTitle("Oh no!")
        .setSubtitle("Something happened...")
        .setButton("Try Again!", v -> {
          progress.setLoading(true);
          emptyView.hide();
          // Try again here
        })
        .commit();

    progress.setLoading(false);
    emptyView.show();
  }

  private void empty() {
    emptyView.newState()
        .setTitle("Oh oh oh!")
        .setSubtitle("The list is empty...")
        .commit();

    progress.setLoading(false);
    emptyView.show();
  }

  private void terminate() {
    alert = new AlertDialog.Builder(this)
        .setTitle(R.string.already_leaving)
        .setMessage(R.string.already_leaving_msg)
        .setNegativeButton(R.string.cancel, (dialog, which) -> { /* Cancel */ })
        .setPositiveButton(R.string.yes, (dialog, which) -> finish())
        .show();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (alert != null) {
      alert.dismiss();
    }
  }
}

package com.prolificinteractive.configurations.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import com.prolificinteractive.configurations.binding.configurations.RecyclerViewConfiguration;

public class RecyclerViewBindings {
  private RecyclerViewBindings() {
    throw new AssertionError("No instances.");
  }

  @BindingAdapter("config") public static void bindRecyclerViewConfiguration(
      final RecyclerView view,
      final RecyclerViewConfiguration config) {
    if (config != null) {
      if (view.getAdapter() == null && config.getAdapter() != null) {
        view.setAdapter(config.getAdapter());
      }
      if (view.getLayoutManager() == null && config.getLayoutManager() != null) {
        view.setLayoutManager(config.getLayoutManager());
      }
    }
  }
}
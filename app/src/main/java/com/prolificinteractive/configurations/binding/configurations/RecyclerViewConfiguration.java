package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;
import com.prolificinteractive.configurations.BR;

public class RecyclerViewConfiguration extends BaseObservable {
  private RecyclerView.LayoutManager layoutManager;
  private RecyclerView.Adapter adapter;

  // Show by default
  private boolean visible = true;

  @Bindable public RecyclerView.LayoutManager getLayoutManager() {
    return layoutManager;
  }

  @Bindable public RecyclerView.Adapter getAdapter() {
    return adapter;
  }

  @Bindable public boolean isVisible() {
    return visible;
  }

  public void setConfig(
      final RecyclerView.LayoutManager layoutManager,
      final RecyclerView.Adapter adapter) {
    this.layoutManager = layoutManager;
    this.adapter = adapter;
    notifyChange();
  }

  public void show() {
    visible = true;
    notifyPropertyChanged(BR.visible);
  }

  public void hide() {
    visible = false;
    notifyPropertyChanged(BR.visible);
  }
}

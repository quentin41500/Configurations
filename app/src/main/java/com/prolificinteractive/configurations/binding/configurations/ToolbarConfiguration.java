package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

public class ToolbarConfiguration extends BaseObservable {
  private String title;
  private View.OnClickListener listener;

  @Bindable public String getTitle() {
    return title;
  }

  @Bindable public View.OnClickListener getListener() {
    return listener;
  }

  public void setConfig(final String title, final View.OnClickListener listener) {
    this.title = title;
    this.listener = listener;
    notifyChange();
  }
}

package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public final class ItemConfiguration extends BaseObservable {
  private String text;

  @Bindable public String getText() {
    return text;
  }

  public void setConfig(final String text) {
    this.text = text;
  }
}

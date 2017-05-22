package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import com.prolificinteractive.configurations.BR;

/**
 * Empty view configuration must be used using the builder.
 */
public class EmptyViewConfiguration extends BaseObservable {

  private String title;
  private String subtitle;
  private String button;
  private View.OnClickListener listener;

  private boolean visible;

  @Bindable public String getTitle() {
    return title;
  }

  @Bindable public String getSubtitle() {
    return subtitle;
  }

  @Bindable public String getButton() {
    return button;
  }

  @Bindable public View.OnClickListener getListener() {
    return listener;
  }

  @Bindable public boolean isVisible() {
    return visible;
  }

  public Builder newState() {
    return new Builder();
  }

  public class Builder {

    private String subtitle;
    private String title;
    private String button;
    private View.OnClickListener listener;

    private Builder() { }

    /**
     * Set a title to the view. Title is invisible is no title is set.
     *
     * @param title Empty view title.
     */
    public Builder setTitle(final String title) {
      this.title = title;
      return this;
    }

    /**
     * Set a subtitle to the view. Subtitle is invisible is no subtitle is set.
     *
     * @param subtitle Empty view subtitle.
     */
    public Builder setSubtitle(final String subtitle) {
      this.subtitle = subtitle;
      return this;
    }

    /**
     * Set button text and listener. Button will be invisible if nothing is set.
     *
     * @param button Button text.
     * @param listener Click listener to set on the button.
     */
    public Builder setButton(final String button, final View.OnClickListener listener) {
      this.button = button;
      this.listener = listener;
      return this;
    }

    public void commit() {
      EmptyViewConfiguration.this.setConfig(title, subtitle, button, listener);
    }
  }

  private void setConfig(
      final String title,
      final String subtitle,
      final String button,
      final View.OnClickListener listener) {
    this.title = title;
    this.subtitle = subtitle;
    this.button = button;
    this.listener = listener;

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
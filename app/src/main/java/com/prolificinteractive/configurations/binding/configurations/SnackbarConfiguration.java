package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.prolificinteractive.configurations.R;
import com.prolificinteractive.configurations.binding.ViewBindings;

/**
 * Snackbar configuration is used to populate a view with a snackbar. It's paired with the {@link
 * BindingAdapter} method {@link ViewBindings#bindSnackBarText(View, SnackbarConfiguration)}.
 * <p>
 * To use it, first declare an instance in your presenter:
 * <pre>
 * <code>SnackbarConfiguration snackbarConfig = new SnackbarConfiguration();</code>
 * </pre>
 * Then use the {@link SnackbarConfiguration#newState(String)} method to populate a new snackbar
 * message:
 *
 * <pre>
 * <code>snackbarConfig.newState(throwable.getMessage())
 *    .setType(SnackbarConfiguration.Type.ERROR)
 *    .setDuration(Snackbar.LENGTH_LONG)
 *    .commit()</code>
 * </pre>
 *
 * </p>
 */
public class SnackbarConfiguration extends BaseObservable {

  /**
   * Possible type of snackbar to show. Each type will then provide a color for the background and
   * the text.
   */
  public enum Type {
    /**
     * Error type of snackbar.
     */
    ERROR(R.style.Snackbar_Alert),

    /**
     * Valid type of snackbar.
     */
    VALID(R.style.Snackbar_Confirm),

    /**
     * Neutral type of snackbar.
     */
    NEUTRAL(R.style.Snackbar_Neutral);

    /**
     * Style id to use on the snackbar.
     */
    @StyleRes public final int styleId;

    Type(@StyleRes final int styleId) {
      this.styleId = styleId;
    }
  }

  /**
   * The message to display.
   */
  private CharSequence msg;

  /**
   * Message duration
   */
  private int duration;

  /**
   * Error Type.
   */
  private Type type;

  @Bindable public CharSequence getMsg() {
    return msg;
  }

  @Bindable public int getDuration() {
    return duration;
  }

  @Bindable public Type getType() {
    return type;
  }

  /**
   * Use this method to populate a new message through the snackbar configuration.
   *
   * @param msg The message you want to display.
   * @return A builder to set other parameters to the snackbar configuration.
   */
  public Builder newState(@NonNull final String msg) {
    return new Builder(msg);
  }

  public class Builder {
    private final CharSequence msg;
    private int duration = Snackbar.LENGTH_SHORT;
    private Type type = Type.NEUTRAL;

    /**
     * The constructor shouldn't be accessed outside the configuration. Please use the {@link
     * SnackbarConfiguration#newState(String)} method instead.
     */
    private Builder(final CharSequence msg) {
      this.msg = msg;
    }

    /**
     * Set duration for the snackbar.
     */
    public Builder setDuration(final int duration) {
      this.duration = duration;
      return Builder.this;
    }

    /**
     * Set the type of snackbar. Each type will then provide a color for the background and the
     * text.
     */
    public Builder setType(final Type type) {
      this.type = type;
      return Builder.this;
    }

    /**
     * Call this method to set the configuration and display the snackbar.
     */
    public void commit() {
      SnackbarConfiguration.this.setConfig(msg, type, duration);
    }
  }

  /**
   * This method shouldn't be accessed outside the configuration. Please use the {@link
   * Builder#commit()} method instead.
   */
  private void setConfig(final CharSequence msg, final Type type, final int duration) {
    this.msg = msg;
    this.type = type;
    this.duration = duration;
    notifyChange();
  }
}
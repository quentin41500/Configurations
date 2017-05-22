
package com.prolificinteractive.configurations.binding;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import com.prolificinteractive.configurations.R;
import com.prolificinteractive.configurations.binding.configurations.SnackbarConfiguration;

/**
 * A class that holds data binding adapters and conversions for classes that extend {@link View}.
 * This class should only be used by the data binding library, as such they must be public.
 * You should not directly invoke any method in this class.
 */
public class ViewBindings {

  private ViewBindings() {
    throw new AssertionError("No instances.");
  }

  @BindingConversion
  public static int convertBooleanToVisibility(final boolean visible) {
    return visible ? View.VISIBLE : View.GONE;
  }

  @BindingAdapter("snackbar")
  public static void bindSnackBarText(final View layout, final SnackbarConfiguration config) {
    if (config != null && config.getMsg() != null) {
      final Snackbar snackbar = Snackbar.make(layout, config.getMsg(), config.getDuration());
      final View view = snackbar.getView();

      final TypedArray ta = layout.getContext().obtainStyledAttributes(
          config.getType().styleId,
          R.styleable.SnackbarStyle
      );

      final int textColor = ta.getColor(R.styleable.SnackbarStyle_android_textColor, 0);
      final int backgroundColor = ta.getColor(R.styleable.SnackbarStyle_android_background, 0);

      view.setBackgroundColor(backgroundColor);

      final TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
      tv.setTextColor(textColor);

      snackbar.show();

      ta.recycle();
    }
  }

  @BindingAdapter("animateAlpha")
  public static void bindAnimateAlpha(final View view, final boolean show) {
    if (show) {
      view.animate()
          .alpha(1)
          .start();
    } else {
      view.setAlpha(0);
    }
  }
}

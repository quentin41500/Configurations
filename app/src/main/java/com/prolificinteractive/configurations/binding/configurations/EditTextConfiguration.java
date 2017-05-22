package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.prolificinteractive.configurations.BR;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class EditTextConfiguration extends BaseObservable {
  private static final String TAG = EditTextConfiguration.class.getSimpleName();

  @Bindable public boolean errorEnabled = false;
  @Bindable public String errorText = null;
  @Bindable public String text;

  /**
   * Reset the error to false and check if the current string text pass the validation
   *
   * @param validation Validating method
   * @param message Error message
   */
  public void validate(final Function<String, Boolean> validation, final String message) {
    Observable.just(text)
        .map(s -> !validation.apply(s))
        .filter(isNotValid -> isNotValid)
        .subscribe(
            isNotValid -> {
              Log.e(TAG, "validate error");
              setError(message);
            },
            error -> Log.e(TAG, "error validating field", error)
        );
  }

  public void setError(final String message) {
    errorText = message;
    errorEnabled = true;
    notifyChange();
  }

  public void resetError() {
    errorEnabled = false;
    errorText = null;
    notifyChange();
  }

  public void setText(final String text) {
    this.text = text;
    errorEnabled = false;
    notifyPropertyChanged(BR.errorEnabled);
  }

  public void clear() {
    text = null;
    errorEnabled = false;
    errorText = null;
    notifyChange();
  }
}

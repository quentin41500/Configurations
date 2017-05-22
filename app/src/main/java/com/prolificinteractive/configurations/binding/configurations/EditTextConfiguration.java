package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.prolificinteractive.configurations.BR;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class EditTextConfiguration extends BaseObservable {

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
              Timber.e("validate error");
              setError(message);
            },
            error -> Timber.e(error, "error validating field")
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

package com.prolificinteractive.configurations.binding.configurations;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.prolificinteractive.configurations.BR;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class ProgressWithTextConfiguration extends BaseObservable {
  private static final String TAG = ProgressWithTextConfiguration.class.getSimpleName();

  /**
   * You can keep this a constant or set it programmatically.
   */
  private static final int DELAY = 10; // Seconds

  private boolean isLoading = false;
  private boolean isTextVisible = false;

  private Disposable disposable;

  @Bindable public boolean isLoading() {
    return isLoading;
  }

  @Bindable public boolean isTextVisible() {
    return isTextVisible;
  }

  public void setLoading(final boolean loading) {
    isLoading = loading;
    isTextVisible = false;
    if (loading) {
      disposable = Observable.just(1)
          .delay(DELAY, TimeUnit.SECONDS)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(ignore -> {
            isTextVisible = true;
            notifyPropertyChanged(BR.textVisible);
          }, error -> Log.e(TAG, "error delay", error));
    } else {
      dispose();
    }
    notifyChange();
  }

  public void dispose() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
  }
}
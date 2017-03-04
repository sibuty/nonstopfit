package ru.digitalwand.nonstopfit.ui.base.mvp;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:41.
 */
public abstract class BasePresenter<T, V extends BaseView<T>> {

  private final CompositeSubscription subscriptions = new CompositeSubscription();
  protected V view;

  public void attachView(@NonNull final V view) {
    checkNotNull(view);
    this.view = view;
  }

  public void detachView() {
    view = null;
    subscriptions.clear();
  }

  public void addSubscription(final Subscription subscription) {
    subscriptions.add(subscription);
  }
}

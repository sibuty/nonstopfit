package ru.digitalwand.nonstopfit.util;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 01.03.2017 21:10.
 */
public class RxBackgoroundWrapper {

  public static <T> Subscription doInBackground(@NonNull Observable<T> observable,
                                                @NonNull Subscriber<T> subscriber) {
    return observable.observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }

  public static <T> Observable<T> doInBackground(@NonNull Observable<T> observable) {
    return observable.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
  }
}

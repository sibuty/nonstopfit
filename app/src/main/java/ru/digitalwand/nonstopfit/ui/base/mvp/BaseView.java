package ru.digitalwand.nonstopfit.ui.base.mvp;

import android.content.Context;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:27.
 */
public interface BaseView<T> {

  Context context();

  void showError(final String message);

  void showLoading();

  void hideLoading();

  boolean isReady();
}

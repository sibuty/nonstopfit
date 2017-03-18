package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;

import ru.digitalwand.nonstopfit.data.entity.LoginResponse;
import ru.digitalwand.nonstopfit.ui.base.mvp.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 23:43.
 */
public interface LoginContract {

  interface View<T> extends BaseView<T> {

    void loginSuccess(@NonNull LoginResponse loginResponse);

    void emailIsEmpty();

    void emailIsInvalid();

    void passwordIsEmpty();

    void setButtonEnterEnable(boolean result);
  }

  interface Presenter {

    void login();

    void verifyPassword(String password);
  }
}

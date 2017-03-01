package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;

import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.LoginResponse;
import ru.digitalwand.nonstopfit.ui.BasePresenter;
import ru.digitalwand.nonstopfit.ui.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 23:43.
 */
public interface LoginContract {

  interface View extends BaseView<Presenter> {
    void loginSuccess(@NonNull LoginResponse loginResponse);
    void loginError(@NonNull String message);
  }

  interface Presenter extends BasePresenter {
    void setLogin(@NonNull Login login);
    void login();
  }
}

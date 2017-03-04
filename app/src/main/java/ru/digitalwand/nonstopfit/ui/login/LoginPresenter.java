package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.ui.base.mvp.BasePresenter;
import ru.digitalwand.nonstopfit.util.RxBackgoroundWrapper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 01.03.2017 20:09.
 */
public class LoginPresenter extends BasePresenter<Login, LoginContract.View<Login>>
    implements LoginContract.Presenter {

  @NonNull
  private final LoginWrapper loginWrapper;
  @Nullable
  private Login login;

  public LoginPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  public void login() {
    checkNotNull(login);
    if (verifyLogin(login)) {
      addSubscription(RxBackgoroundWrapper.doInBackground(loginWrapper.login(login))
                          .subscribe(view::loginSuccess, throwable -> {
                            throwable.printStackTrace();
                            view.showError(throwable.getMessage());
                          }));
    }
  }

  @Override
  public void setLogin(@NonNull final Login login) {
    checkNotNull(login);
    this.login = login;
  }

  private boolean verifyLogin(@NonNull final Login login) {
    if (StringUtils.isEmpty(login.userName)) {
      view.loginIsEmpty();
      return false;
    }
    if (StringUtils.isEmpty(login.password)) {
      view.passwordIsEmpty();
      return false;
    }
    return true;
  }
}

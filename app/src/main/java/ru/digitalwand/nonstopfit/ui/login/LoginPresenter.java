package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.ui.base.mvp.BasePresenter;

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

  public LoginPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  public void login() {
    final Login login = getData();
    checkNotNull(login);
    final LoginContract.View view = getView();
    if (verify(login, view)) {
      addSubscription(loginWrapper.wrappedLogin(login).subscribe(view::loginSuccess, throwable -> {
        throwable.printStackTrace();
        view.showError(throwable.getMessage());
      }));
    }
  }

  @Override
  public void setData(@NonNull final Login login) {
    checkNotNull(login);
    super.setData(login);
  }

  @Override
  protected boolean verify(@NonNull final Login login, @NonNull final LoginContract.View view) {
    boolean result = true;
    if (StringUtils.isEmpty(login.userName)) {
      view.loginIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(login.password)) {
      view.passwordIsEmpty();
      result = false;
    }
    return result;
  }
}

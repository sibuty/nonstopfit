package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.adapter.rxjava.HttpException;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.AccessToken;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.provider.PreferencesManager;
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
  @NonNull
  private final PreferencesManager preferencesManager;

  public LoginPresenter(@NonNull final LoginWrapper loginWrapper,
                        @NonNull final PreferencesManager preferencesManager) {
    this.loginWrapper = loginWrapper;
    this.preferencesManager = preferencesManager;
  }

  @Override
  public void login() {
    final Login login = getData();
    checkNotNull(login);
    final LoginContract.View view = getView();
    if (verifyData(login, view)) {
      view.showLoading();
      addSubscription(loginWrapper
                          .wrappedLogin(login)
                          .subscribe(this::onSuccess, this::onError, view::hideLoading));
    }
  }

  @Override
  public void verifyPassword(final String password) {
    boolean result = false;
    if (StringUtils.isNotEmpty(password)) {
      result = password.length() >= 6;
    }
    getView().setButtonEnterEnable(result);
  }

  @Override
  public void setData(@NonNull final Login login) {
    checkNotNull(login);
    super.setData(login);
  }

  @Override
  protected boolean verifyData(@NonNull final Login login, @NonNull final LoginContract.View view) {
    boolean result = true;
    if (StringUtils.isEmpty(login.userName)) {
      view.emailIsEmpty();
      result = false;
    } else if (login.userName.contains("@") && !login.userName.matches(
        "[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z0-9._-]{2,3}")) {
      view.emailIsInvalid();
      result = false;
    }
    if (StringUtils.isEmpty(login.password)) {
      view.passwordIsEmpty();
      result = false;
    }
    return result;
  }

  private void onSuccess(final AccessToken accessToken) {
    preferencesManager.setAccessToken(accessToken);
    getView().startSmsApplyActivity();
  }

  private void onError(final Throwable throwable) {
    throwable.printStackTrace();
    String message = null;
    if (throwable instanceof HttpException) {
      if (((HttpException) throwable).code() == HttpURLConnection.HTTP_BAD_REQUEST
          || ((HttpException) throwable).code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
        message = getView().context().getString(R.string.error_login_and_password_incorrect);
      }
    } else if (throwable instanceof IOException) {
      message = getView().context().getString(R.string.error_authorization_failed);
    } else {
      message = throwable.getMessage();
    }
    getView().showError(message);
    getView().hideLoading();
  }
}

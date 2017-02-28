package ru.digitalwand.nonstopfit.data.wrapper;

import android.support.annotation.StringRes;

import javax.inject.Inject;

import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.LoginResponse;
import ru.digitalwand.nonstopfit.data.entity.ResetPasswordResponse;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
import ru.digitalwand.nonstopfit.data.entity.User;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;
import rx.Observable;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:27.
 */
public class LoginWrapper {

  @Inject
  protected NetworkProvider networkProvider;
  protected App app;

  public LoginWrapper(App app) {
    this.app = app;
  }

  public Observable<SignResponse> signUp(Sign sign) {
    return networkProvider.signUp(sign).flatMap(this::checkResponse);
  }

  public Observable<LoginResponse> login(Login login) {
    return networkProvider.login(login).flatMap(this::checkResponse);
  }

  public Observable<ResetPasswordResponse> signUp(User user) {
    return networkProvider.resetPassword(user).flatMap(this::checkResponse);
  }

  private <T> Observable<T> checkResponse(T response) {
    return checkResponse(R.string.error_response_is_null, response);
  }

  private <T> Observable<T> checkResponse(@StringRes final int error, T response) {
    return response == null
           ? Observable.error(new Throwable(app.getString(error)))
           : Observable.just(response);
  }
}

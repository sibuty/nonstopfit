package ru.digitalwand.nonstopfit.data.wrapper;

import android.support.annotation.StringRes;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Credentials;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.BuildConfig;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.AccessToken;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.ResetPasswordResponse;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
import ru.digitalwand.nonstopfit.data.entity.User;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;
import ru.digitalwand.nonstopfit.util.RxBackgoroundWrapper;
import rx.Observable;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:27.
 */
public class LoginWrapper {

  protected NetworkProvider networkProvider;
  protected App app;

  public LoginWrapper(App app, NetworkProvider networkProvider) {
    this.app = app;
    this.networkProvider = networkProvider;
  }

  public Observable<SignResponse> signUp(Sign sign) {
    return networkProvider.signUp(sign).flatMap(this::checkResponse);
  }

  public Observable<AccessToken> login(Login login) {
    final Map<String, String> fields = new HashMap<>();
    fields.put("grant_type", "password");
    fields.put("username", login.userName);
    fields.put("password", login.password);
    return networkProvider
        .login(Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET), fields)
        .flatMap(this::checkResponse);
  }

  public Observable<ResetPasswordResponse> resetPassword(User user) {
    return networkProvider.resetPassword(user).flatMap(this::checkResponse);
  }

  public Observable<String> verifySmsCode(String smsCode) {
    return networkProvider.verifySmsCode(smsCode).flatMap(this::checkResponse);
  }

  public Observable<SignResponse> wrappedSignUp(Sign sign) {
    return RxBackgoroundWrapper.doInBackground(signUp(sign));
  }

  public Observable<AccessToken> wrappedLogin(Login login) {
    return RxBackgoroundWrapper.doInBackground(login(login));
  }

  public Observable<ResetPasswordResponse> wrappedResetPassword(User user) {
    return RxBackgoroundWrapper.doInBackground(resetPassword(user));
  }

  public Observable<String> wrappedVerifySmsCode(String smsCode) {
    return RxBackgoroundWrapper.doInBackground(verifySmsCode(smsCode));
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

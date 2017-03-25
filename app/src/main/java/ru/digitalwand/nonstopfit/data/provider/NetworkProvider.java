package ru.digitalwand.nonstopfit.data.provider;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.digitalwand.nonstopfit.BuildConfig;
import ru.digitalwand.nonstopfit.data.entity.AccessToken;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.ResetPasswordResponse;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
import ru.digitalwand.nonstopfit.data.entity.User;
import ru.digitalwand.nonstopfit.data.provider.interceptor.AuthenticationInterceptor;
import rx.Observable;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:52.
 */
public interface NetworkProvider {

  @POST(BuildConfig.MAIN_CONTROLLER + "sign-up/")
  Observable<SignResponse> signUp(@Body Sign sign);

  @POST(BuildConfig.MAIN_CONTROLLER + "login/")
  Observable<AccessToken> login(@Header(AuthenticationInterceptor.HEADER_KEY) String basicToken,
                                @Body Login login);

  @POST(BuildConfig.MAIN_CONTROLLER + "sendPassword-password/")
  Observable<ResetPasswordResponse> resetPassword(@Body User user);

  @GET(BuildConfig.MAIN_CONTROLLER + "verifyData-sms-code/")
  Observable<String> verifySmsCode(@Query("sms_code") String smsCode);
}

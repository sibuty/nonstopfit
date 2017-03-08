package ru.digitalwand.nonstopfit.data.provider;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.LoginResponse;
import ru.digitalwand.nonstopfit.data.entity.ResetPasswordResponse;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
import ru.digitalwand.nonstopfit.data.entity.User;
import rx.Observable;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:52.
 */
public interface NetworkProvider {

  @POST("/api/sign-up/")
  Observable<SignResponse> signUp(@Body Sign sign);

  @POST("/api/login/")
  Observable<LoginResponse> login(@Body Login login);

  @POST("/api/sendPassword-password/")
  Observable<ResetPasswordResponse> resetPassword(@Body User user);

  @GET("/api/verify-sms-code/")
  Observable<String> verifySmsCode(@Query("sms_code") String smsCode);
}

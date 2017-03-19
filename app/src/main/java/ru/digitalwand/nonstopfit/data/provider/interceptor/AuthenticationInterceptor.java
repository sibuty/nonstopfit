package ru.digitalwand.nonstopfit.data.provider.interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import ru.digitalwand.nonstopfit.data.provider.PreferencesManager;

public class AuthenticationInterceptor implements Interceptor {

  public static final String HEADER_KEY = "Authorization";
  @NonNull
  private final PreferencesManager preferencesManager;
  @Nullable
  private final String authToken;

  public AuthenticationInterceptor(@NonNull final PreferencesManager preferencesManager) {
    this(preferencesManager, null);
  }

  public AuthenticationInterceptor(@NonNull final PreferencesManager preferencesManager,
                                   @Nullable final String token) {
    this.preferencesManager = preferencesManager;
    this.authToken = token;
  }

  @Override
  public Response intercept(final Chain chain) throws IOException {
    return chain.proceed(chain.request().headers(HEADER_KEY).isEmpty() ? chain
        .request()
        .newBuilder()
        .header(HEADER_KEY, StringUtils.isBlank(authToken) ? "" : authToken)
        .build() : chain.request());
  }
}
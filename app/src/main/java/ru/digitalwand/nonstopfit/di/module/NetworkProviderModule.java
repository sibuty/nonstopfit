package ru.digitalwand.nonstopfit.di.module;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;
import ru.digitalwand.nonstopfit.data.provider.PreferencesManager;
import ru.digitalwand.nonstopfit.data.provider.interceptor.AuthenticationInterceptor;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:32.
 */
@Module
public class NetworkProviderModule {

  private static final long TIMEOUT = 35L;
  private final String baseUrl;

  public NetworkProviderModule(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Provides
  @Singleton
  Cache provideHttpCache(final App app) {
    final long cacheSize = 10L * 1024L * 1024L;
    return new Cache(app.getCacheDir(), cacheSize);
  }

  @Named("logging")
  @Provides
  @Singleton
  Interceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(BODY);
  }

  @Named("auth")
  @Provides
  @Singleton
  Interceptor provideAuthInterceptor(final PreferencesManager preferencesManager) {
    return new AuthenticationInterceptor(preferencesManager);
  }

  @Provides
  @Singleton
  ObjectMapper provideJacksonMapper() {
    return new ObjectMapper()
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .setPropertyNamingStrategy(SNAKE_CASE);
  }

  @Provides
  @Singleton
  OkHttpClient provideOkhttpClient(final Cache cache,
                                   @Named("logging") final Interceptor authInterceptor,
                                   @Named("auth") final Interceptor loggingInterceptor) {
    return new OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .cache(cache)
        .build();
  }

  @Provides
  @Singleton
  NetworkProvider provideRetrofit(final ObjectMapper mapper, final OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(okHttpClient)
        .build()
        .create(NetworkProvider.class);
  }
}

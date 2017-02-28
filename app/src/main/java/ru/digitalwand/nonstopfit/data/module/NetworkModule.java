package ru.digitalwand.nonstopfit.data.module;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:32.
 */
@Module
public class NetworkModule {

  private final String baseUrl;

  public NetworkModule(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Provides
  @Singleton
  Cache provideHttpCache(final Application application) {
    final long cacheSize = 10L * 1024L * 1024L;
    return new Cache(application.getCacheDir(), cacheSize);
  }

  @Provides
  @Singleton
  HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(BODY);
  }

  @Provides
  @Singleton
  ObjectMapper provideJacksonMapper() {
    return new ObjectMapper().disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .setPropertyNamingStrategy(SNAKE_CASE);
  }

  @Provides
  @Singleton
  OkHttpClient provideOkhttpClient(final Cache cache, final HttpLoggingInterceptor interceptor) {
    return new OkHttpClient.Builder().addInterceptor(interceptor).cache(cache).build();
  }

  @Provides
  @Singleton
  NetworkProvider provideRetrofit(final ObjectMapper mapper, final OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(baseUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(okHttpClient)
        .build()
        .create(NetworkProvider.class);
  }
}

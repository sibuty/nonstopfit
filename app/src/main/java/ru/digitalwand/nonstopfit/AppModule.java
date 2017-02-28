package ru.digitalwand.nonstopfit;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:31.
 */
@Module
public class AppModule {

  private final Application application;

  public AppModule(final Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return application;
  }
}

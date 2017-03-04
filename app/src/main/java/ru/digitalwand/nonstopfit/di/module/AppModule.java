package ru.digitalwand.nonstopfit.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.App;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:31.
 */
@Module(includes = NetworkProviderModule.class)
public class AppModule {

  private final App app;

  public AppModule(final App app) {
    this.app = app;
  }

  @Provides
  @Singleton
  App provideApp() {
    return app;
  }
}

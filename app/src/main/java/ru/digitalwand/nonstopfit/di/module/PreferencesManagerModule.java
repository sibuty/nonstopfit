package ru.digitalwand.nonstopfit.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.provider.PreferencesManager;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 19.03.2017 20:52.
 */
@Module
public class PreferencesManagerModule {

  @Provides
  @Singleton
  PreferencesManager providePreferencesManager(final App app) {
    return new PreferencesManager(app);
  }
}

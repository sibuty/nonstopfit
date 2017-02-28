package ru.digitalwand.nonstopfit.data.wrapper.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:27.
 */
@Module
public class LoginWrapperModule {

  @Provides
  @Singleton
  LoginWrapper provideLoginWrapper(App app) {
    return new LoginWrapper(app);
  }
}

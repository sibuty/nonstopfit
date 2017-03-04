package ru.digitalwand.nonstopfit.di.module.login;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.scope.Login;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:27.
 */
@Module
public class LoginWrapperModule {

  @Provides
  @Login
  LoginWrapper provideLoginWrapper(App app, NetworkProvider networkProvider) {
    return new LoginWrapper(app, networkProvider);
  }
}

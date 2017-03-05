package ru.digitalwand.nonstopfit.di.module;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.scope.login.Login;
import ru.digitalwand.nonstopfit.ui.login.LoginPresenter;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:06.
 */
@Module
public class LoginPresenterModule {

  @Provides
  @Login
  LoginPresenter provideLoginPresenter(LoginWrapper loginWrapper) {
    return new LoginPresenter(loginWrapper);
  }
}

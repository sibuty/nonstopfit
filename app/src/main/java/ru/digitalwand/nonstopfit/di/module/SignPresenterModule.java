package ru.digitalwand.nonstopfit.di.module;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.scope.Sign;
import ru.digitalwand.nonstopfit.ui.sign.SignPresenter;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 21:14.
 */
@Module
public class SignPresenterModule {

  @Provides
  @Sign
  SignPresenter provideLoginPresenter(LoginWrapper loginWrapper) {
    return new SignPresenter(loginWrapper);
  }
}

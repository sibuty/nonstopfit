package ru.digitalwand.nonstopfit.di.module;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.scope.login.Reset;
import ru.digitalwand.nonstopfit.ui.login.reset.ResetPresenter;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 23:51.
 */
@Module
public class ResetPresenterModule {

  @Provides
  @Reset
  ResetPresenter provideResetPreseter(LoginWrapper loginWrapper) {
    return new ResetPresenter(loginWrapper);
  }
}

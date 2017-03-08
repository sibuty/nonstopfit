package ru.digitalwand.nonstopfit.di.module;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.scope.login.Sms;
import ru.digitalwand.nonstopfit.ui.login.sms.SmsApplyPresenter;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 08.03.2017 15:19.
 */
@Module
public class SmsApplyPresenterModule {

  @Sms
  @Provides
  SmsApplyPresenter provideSmsApplyPresenter(final LoginWrapper loginWrapper) {
    return new SmsApplyPresenter(loginWrapper);
  }
}

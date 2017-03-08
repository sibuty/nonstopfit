package ru.digitalwand.nonstopfit.di.component;

import dagger.Subcomponent;
import ru.digitalwand.nonstopfit.di.module.SmsApplyPresenterModule;
import ru.digitalwand.nonstopfit.di.scope.login.Sms;
import ru.digitalwand.nonstopfit.ui.login.sms.SmsApplyActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 08.03.2017 15:19.
 */
@Sms
@Subcomponent(modules = SmsApplyPresenterModule.class)
public interface SmsApplyComponent {
  void inject(SmsApplyActivity activity);
}

package ru.digitalwand.nonstopfit.di.component;

import dagger.Subcomponent;
import ru.digitalwand.nonstopfit.di.module.ResetPresenterModule;
import ru.digitalwand.nonstopfit.di.scope.login.Reset;
import ru.digitalwand.nonstopfit.ui.login.reset.ResetActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 23:50.
 */
@Reset
@Subcomponent(modules = ResetPresenterModule.class)
public interface ResetActivityComponent {
  void inject(ResetActivity activity);
}

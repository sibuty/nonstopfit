package ru.digitalwand.nonstopfit.di.component.login;

import dagger.Subcomponent;
import ru.digitalwand.nonstopfit.di.module.login.LoginWrapperModule;
import ru.digitalwand.nonstopfit.di.scope.Login;
import ru.digitalwand.nonstopfit.ui.login.LoginPresenter;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 8:14.
 */
@Login
@Subcomponent(modules = LoginWrapperModule.class)
public interface LoginPresenterComponent {
  void inject(LoginPresenter loginPresenter);
}

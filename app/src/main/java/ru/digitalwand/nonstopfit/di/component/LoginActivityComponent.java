package ru.digitalwand.nonstopfit.di.component;

import dagger.Subcomponent;
import ru.digitalwand.nonstopfit.di.module.LoginPresenterModule;
import ru.digitalwand.nonstopfit.di.scope.Login;
import ru.digitalwand.nonstopfit.ui.login.LoginActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:06.
 */
@Login
@Subcomponent(modules = LoginPresenterModule.class)
public interface LoginActivityComponent {
  void inject(LoginActivity loginActivity);
}

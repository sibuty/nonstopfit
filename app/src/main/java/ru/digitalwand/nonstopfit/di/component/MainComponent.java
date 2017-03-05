package ru.digitalwand.nonstopfit.di.component;

import dagger.Component;
import ru.digitalwand.nonstopfit.di.module.LoginPresenterModule;
import ru.digitalwand.nonstopfit.di.module.MainModule;
import ru.digitalwand.nonstopfit.di.module.SignPresenterModule;
import ru.digitalwand.nonstopfit.di.scope.Main;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 11:38.
 */
@Main
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
  LoginActivityComponent plus(LoginPresenterModule module);

  SignActivityComponent plus(SignPresenterModule module);
}

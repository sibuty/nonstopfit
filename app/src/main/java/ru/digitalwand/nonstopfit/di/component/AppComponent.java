package ru.digitalwand.nonstopfit.di.component;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 21:17.
 */

import javax.inject.Singleton;

import dagger.Component;
import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.provider.NetworkProvider;
import ru.digitalwand.nonstopfit.di.component.login.LoginPresenterComponent;
import ru.digitalwand.nonstopfit.di.module.AppModule;
import ru.digitalwand.nonstopfit.di.module.login.LoginWrapperModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

  App app();

  NetworkProvider networkProvider();

  LoginPresenterComponent pluse(LoginWrapperModule module);
}
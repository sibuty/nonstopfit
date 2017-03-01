package ru.digitalwand.nonstopfit;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 21:17.
 */

import javax.inject.Singleton;

import dagger.Component;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.module.NetworkModule;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.data.wrapper.module.LoginWrapperModule;
import ru.digitalwand.nonstopfit.ui.login.LoginActivity;
import ru.digitalwand.nonstopfit.ui.login.LoginPresenter;

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class, LoginWrapperModule.class})
public interface AppComponent {

  void inject(LoginWrapper loginWrapper);


}

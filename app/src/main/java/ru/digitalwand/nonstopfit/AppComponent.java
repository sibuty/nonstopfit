package ru.digitalwand.nonstopfit;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 21:17.
 */

import javax.inject.Singleton;

import dagger.Component;
import ru.digitalwand.nonstopfit.data.module.NetworkModule;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class })
public interface AppComponent {

  void inject(LoginWrapper loginWrapper);

}

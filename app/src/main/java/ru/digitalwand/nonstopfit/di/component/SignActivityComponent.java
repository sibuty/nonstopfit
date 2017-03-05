package ru.digitalwand.nonstopfit.di.component;

import dagger.Subcomponent;
import ru.digitalwand.nonstopfit.di.module.SignPresenterModule;
import ru.digitalwand.nonstopfit.di.scope.Sign;
import ru.digitalwand.nonstopfit.ui.sign.SignActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 21:16.
 */
@Sign
@Subcomponent(modules = SignPresenterModule.class)
public interface SignActivityComponent {
  void inject(SignActivity activity);
}

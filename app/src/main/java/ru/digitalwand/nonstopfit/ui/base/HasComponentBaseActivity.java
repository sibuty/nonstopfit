package ru.digitalwand.nonstopfit.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.di.HasComponent;
import ru.digitalwand.nonstopfit.di.component.DaggerMainComponent;
import ru.digitalwand.nonstopfit.di.component.MainComponent;
import ru.digitalwand.nonstopfit.di.module.MainModule;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 21:19.
 */
public abstract class HasComponentBaseActivity<T> extends BaseActivity implements HasComponent<T> {

  public static MainComponent buildMainComponent() {
    return DaggerMainComponent.builder()
                              .appComponent(App.getAppComponent())
                              .mainModule(new MainModule())
                              .build();
  }

  protected abstract void applyInject();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    applyInject();
  }
}

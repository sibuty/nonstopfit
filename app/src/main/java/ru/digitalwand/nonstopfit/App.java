package ru.digitalwand.nonstopfit;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.digitalwand.nonstopfit.di.component.DaggerAppComponent;
import ru.digitalwand.nonstopfit.di.module.provider.NetworkProviderModule;
import ru.digitalwand.nonstopfit.di.component.AppComponent;
import ru.digitalwand.nonstopfit.di.module.AppModule;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:47.
 */
public class App extends Application {

  private static AppComponent appComponent;

  @NonNull
  public static AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = buildComponent();
  }

  protected AppComponent buildComponent() {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .networkProviderModule(new NetworkProviderModule(BuildConfig.SERVER_URL))
        .build();
  }
}

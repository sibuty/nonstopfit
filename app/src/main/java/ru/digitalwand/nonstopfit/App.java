package ru.digitalwand.nonstopfit;

import android.app.Application;
import android.support.annotation.NonNull;

import ru.digitalwand.nonstopfit.data.module.NetworkModule;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 20:47.
 */
public class App extends Application {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .networkModule(new NetworkModule(BuildConfig.SERVER_URL))
        .build();
  }

  @NonNull
  public AppComponent getAppComponent() {
    return appComponent;
  }
}

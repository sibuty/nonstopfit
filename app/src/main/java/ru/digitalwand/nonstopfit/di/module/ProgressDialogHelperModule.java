package ru.digitalwand.nonstopfit.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.digitalwand.nonstopfit.util.ProgressDialogHelper;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 25.03.2017 18:04.
 */
@Module
public class ProgressDialogHelperModule {

  @Singleton
  @Provides
  ProgressDialogHelper provideProgressDialogHelper() {
    return new ProgressDialogHelper();
  }
}

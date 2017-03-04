package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.di.module.login.LoginWrapperModule;
import ru.digitalwand.nonstopfit.util.RxBackgoroundWrapper;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 01.03.2017 20:09.
 */
public class LoginPresenter implements LoginContract.Presenter {

  @NonNull
  private final LoginContract.View loginView;
  @Inject
  protected LoginWrapper loginWrapper;
  @NonNull
  private CompositeSubscription subscriptions;
  @Nullable
  private Login login;

  public LoginPresenter(@NonNull final LoginContract.View loginView) {
    this.loginView = loginView;
    this.subscriptions = new CompositeSubscription();
    App.getAppComponent().pluse(new LoginWrapperModule()).inject(this);
  }

  @Override
  public void login() {
    if (verifyLogin(login)) {
      subscriptions.add(RxBackgoroundWrapper.doInBackground(loginWrapper.login(login))
                            .subscribe(loginView::loginSuccess, throwable -> {
                              throwable.printStackTrace();
                              loginView.loginError(throwable.getMessage());
                            }));
    }
  }

  @Override
  public void subscribe() {
    login();
  }

  @Override
  public void unsubscribe() {
    subscriptions.clear();
  }

  @Override
  public void setLogin(@NonNull final Login login) {
    checkNotNull(login);
    this.login = login;
  }

  public boolean verifyLogin(@NonNull final Login login) {
    checkNotNull(login);
    if (StringUtils.isEmpty(login.userName)) {
      loginView.loginIsEmpty();
      return false;
    }
    if (StringUtils.isEmpty(login.password)) {
      loginView.passwordIsEmpty();
      return false;
    }
    return true;
  }
}

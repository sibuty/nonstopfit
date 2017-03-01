package ru.digitalwand.nonstopfit.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
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
  }

  @Override
  public void login() {
    subscriptions.add(RxBackgoroundWrapper.doInBackground(loginWrapper.login(login))
                          .subscribe(loginView::loginSuccess, throwable -> {
                            throwable.printStackTrace();
                            loginView.loginError(throwable.getMessage());
                          }));
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
}

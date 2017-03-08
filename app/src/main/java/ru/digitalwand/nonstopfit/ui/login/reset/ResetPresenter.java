package ru.digitalwand.nonstopfit.ui.login.reset;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.entity.User;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.ui.base.mvp.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 23:49.
 */
public class ResetPresenter extends BasePresenter<User, ResetContract.View<User>>
    implements ResetContract.Presenter {

  @NonNull
  protected final LoginWrapper loginWrapper;

  public ResetPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  public void sendPassword() {
    final User user = getData();
    checkNotNull(user);
    final ResetContract.View<User> view = getView();
    if (verifyData(user, view)) {
      addSubscription(
          loginWrapper.wrappedResetPassword(user).subscribe(view::passwordWasSent, throwable -> {
            throwable.printStackTrace();
            view.showError(throwable.getMessage());
          }));
    }
  }

  @Override
  protected boolean verifyData(final User user, final ResetContract.View<User> view) {
    boolean result = true;
    if (StringUtils.isEmpty(user.userName) && StringUtils.isEmpty(user.email)) {
      view.fieldIsEmpty();
      result = false;
    }
    if (StringUtils.isNotEmpty(user.email) && !user.email.matches(
        "[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z0-9._-]{2,3}")) {
      view.emailIsInvalid();
      result = false;
    }
    return result;
  }
}

package ru.digitalwand.nonstopfit.ui.login.sign;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.ui.base.mvp.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 20:59.
 */
public class SignPresenter extends BasePresenter<Sign, SignContract.View<Sign>>
    implements SignContract.Presenter {

  @NonNull
  private final LoginWrapper loginWrapper;

  public SignPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  public void signUp() {
    final Sign sign = getData();
    checkNotNull(sign);
    final SignContract.View view = getView();
    if (verifyData(sign, view)) {
      addSubscription(loginWrapper.wrappedSignUp(sign).subscribe(view::signSucsess, throwable -> {
        throwable.printStackTrace();
        view.showError(throwable.getMessage());
      }));
    }
  }

  @Override
  protected boolean verifyData(@NonNull final Sign sign, @NonNull final SignContract.View view) {
    boolean result = true;
    if (StringUtils.isEmpty(sign.userName)) {
      view.loginIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(sign.password)) {
      view.passwordIsEmpty();
      result = false;
    } else if (StringUtils.isEmpty(sign.passwordConfirm) || !sign.password.equals(
        sign.passwordConfirm)) {
      view.passwordsDontMatch();
      result = false;
    }
    if (StringUtils.isEmpty(sign.email)) {
      view.emailIsEmpty();
      result = false;
    } else if (!sign.email.matches("[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z0-9._-]{2,3}")) {
      view.emailIsInvalid();
      result = false;
    }
    return result;
  }
}

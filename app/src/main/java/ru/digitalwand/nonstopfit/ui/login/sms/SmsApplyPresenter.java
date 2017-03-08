package ru.digitalwand.nonstopfit.ui.login.sms;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;
import ru.digitalwand.nonstopfit.ui.base.mvp.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 08.03.2017 14:47.
 */
public class SmsApplyPresenter extends BasePresenter<String, SmsApplyContract.View<String>>
    implements SmsApplyContract.Presenter {

  @NonNull
  private final LoginWrapper loginWrapper;

  public SmsApplyPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  protected boolean verify(String data, SmsApplyContract.View<String> view) {
    boolean result = true;
    if (StringUtils.isEmpty(data)) {
      view.showEmptySmsCodeError();
      result = false;
    }
    return result;
  }

  @Override
  public void applyCode() {
    final String smsCode = getData();
    checkNotNull(smsCode);
    final SmsApplyContract.View<String> view = getView();
    view.showLoading();
    addSubscription(
        loginWrapper.wrappedVerifySmsCode(smsCode).subscribe(view::smsApplySuccsess, throwable -> {
          throwable.printStackTrace();
          view.showError(throwable.getMessage());
          view.hideLoading();
        }, view::hideLoading));
  }
}

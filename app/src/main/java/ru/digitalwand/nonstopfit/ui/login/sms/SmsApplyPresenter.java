package ru.digitalwand.nonstopfit.ui.login.sms;

import android.content.IntentFilter;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import ru.digitalwand.nonstopfit.data.receiver.SmsCodeReceiver;
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
  @NonNull
  private final SmsCodeReceiver receiver = new SmsCodeReceiver(this::onReceive);
  @Nullable
  private IntentFilter intentFilter;

  public SmsApplyPresenter(@NonNull final LoginWrapper loginWrapper) {
    this.loginWrapper = loginWrapper;
  }

  @Override
  public void fillSmsField() {
    if (getView().isReady()) {
      getView().setSmsCode(getData());
    }
  }

  @Override
  public void registerSmsCodeReceiver() {
    if (getView().isReady()) {
      if (intentFilter == null) {
        intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
      }
      getView().context().registerReceiver(receiver, intentFilter);
    }
  }

  @Override
  public void unregisterSmsCodeReceiver() {
    if (getView().isReady()) {
      getView().context().unregisterReceiver(receiver);
    }
  }

  private void onReceive(final String smsCode) {
    setData(smsCode);
    fillSmsCodeField();
  }

  @Override
  public void fillSmsCodeField() {
    if (getView().isReady()) {
      getView().setSmsCode(getData());
    }
  }

  @Override
  protected boolean verifyData(String data, SmsApplyContract.View<String> view) {
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
        loginWrapper.wrappedVerifySmsCode(smsCode).subscribe(view::smsApplySuccess, throwable -> {
          throwable.printStackTrace();
          view.showError(throwable.getMessage());
          view.hideLoading();
        }, view::hideLoading));
  }
}

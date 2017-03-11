package ru.digitalwand.nonstopfit.ui.login.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import ru.digitalwand.nonstopfit.R;
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
    implements SmsApplyContract.Presenter, PermissionListener {

  @NonNull
  private final LoginWrapper loginWrapper;
  @NonNull
  private final SmsCodeReceiver receiver = new SmsCodeReceiver(this::onReceive);
  @Nullable
  private IntentFilter intentFilter;
  private boolean wasGranted;

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
    new TedPermission(getView().context())
        .setPermissionListener(this)
        .setRationaleMessage(R.string.message_need_sms_permissions)
        .setRationaleConfirmText(R.string.button_next)
        .setDeniedMessage(R.string.error_denied_sms_permission)
        .setDeniedCloseButtonText(R.string.button_close)
        .setGotoSettingButtonText(R.string.button_settings)
        .setPermissions(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS)
        .check();
  }

  @Override
  public void unregisterSmsCodeReceiver() {
    if (wasGranted && getView().isReady()) {
      getView().context().unregisterReceiver(receiver);
    }
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
    addSubscription(loginWrapper
                        .wrappedVerifySmsCode(smsCode)
                        .subscribe(this::onSuccess, this::onError, view::hideLoading));
  }

  @Override
  public void onPermissionGranted() {
    wasGranted = true;
    if (getView().isReady()) {
      if (intentFilter == null) {
        intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
      }
      getView().context().registerReceiver(receiver, intentFilter);
    }
  }

  @Override
  public void onPermissionDenied(final ArrayList<String> deniedPermissions) {
    wasGranted = false;
  }

  private void onReceive(final String smsCode) {
    if (StringUtils.isNotEmpty(smsCode)) {
      setData(smsCode);
      fillSmsCodeField();
    }
  }

  private void onSuccess(final String response) {
    getView().smsApplySuccess();
  }

  private void onError(final Throwable throwable) {
    throwable.printStackTrace();
    getView().hideLoading();
  }
}

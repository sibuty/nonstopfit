package ru.digitalwand.nonstopfit.ui.login.sms;

import ru.digitalwand.nonstopfit.ui.base.mvp.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 08.03.2017 14:44.
 */
public interface SmsApplyContract {

  interface View<T> extends BaseView<T> {

    void showEmptySmsCodeError();

    void setSmsCode(final String smsCode);

    void smsApplySuccess();
  }

  interface Presenter {

    void fillSmsField();

    void registerSmsCodeReceiver();

    void unregisterSmsCodeReceiver();

    void fillSmsCodeField();

    void applyCode();
  }
}

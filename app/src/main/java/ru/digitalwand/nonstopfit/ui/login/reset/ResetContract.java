package ru.digitalwand.nonstopfit.ui.login.reset;

import ru.digitalwand.nonstopfit.data.entity.ResetPasswordResponse;
import ru.digitalwand.nonstopfit.ui.base.mvp.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 23:43.
 */
public interface ResetContract {

  interface View<T> extends BaseView<T> {
    void passwordWasSent(ResetPasswordResponse response);

    void fieldIsEmpty();

    void emailIsInvalid();
  }

  interface Presenter {
    void sendPassword();
  }
}

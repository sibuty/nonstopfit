package ru.digitalwand.nonstopfit.ui.login.sign;

import ru.digitalwand.nonstopfit.data.entity.SignResponse;
import ru.digitalwand.nonstopfit.ui.base.mvp.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:02.
 */
public interface SignContract {

  interface View<T> extends BaseView<T> {

    void signSucsess(SignResponse signResponse);

    void loginIsEmpty();

    void passwordIsEmpty();

    void passwordsDontMatch();

    void emailIsEmpty();

    void emailIsInvalid();
  }

  interface Presenter {

    void signUp();
  }
}

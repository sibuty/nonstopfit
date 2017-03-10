package ru.digitalwand.nonstopfit.ui.login.sign;

import ru.digitalwand.nonstopfit.ui.base.mvp.BaseView;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:02.
 */
public interface SignContract {

  interface View<T> extends BaseView<T> {

    void onSignSuccsess();

    void errorFirstnameIsEmpty();

    void errorSurnameIsEmpty();

    void errorPhoneIsEmpty();

    void errorEmailIsEmpty();

    void errorEmailIsInvalid();

    void errorPasswordIsEmpty();

    void errorPasswordsDontMatch();

    void errorBirthdayIsEmpty();

    void errorCityIsNotSelected();

    void errorDateIsInvalid();
  }

  interface Presenter {

    void signUp();

    void setBirthdayDate(String stringDate);

    boolean verifyBirthdayDate(String stringDate);
  }
}

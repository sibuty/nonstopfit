package ru.digitalwand.nonstopfit.ui.login.sign;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.digitalwand.nonstopfit.BuildConfig;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
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
      addSubscription(loginWrapper.wrappedSignUp(sign)
                                  .subscribe(this::onSuccess, this::onError,
                                             getView()::onSignSuccsess));
    }
  }

  @Override
  public void setBirthdayDate(final String stringDate) {
    if (verifyBirthdayDate(stringDate)) {
      try {
        @SuppressLint("SimpleDateFormat") final Date date =
            new SimpleDateFormat(BuildConfig.SIMPLE_DATE_FORMAT).parse(stringDate);
        final Sign sign = getData();
        sign.dateBirthday = date.getTime();
        setData(sign);
      } catch (final ParseException e) {
        e.printStackTrace();
        getView().errorDateIsInvalid();
      }
    }
  }

  @Override
  public boolean verifyBirthdayDate(final String stringDate) {
    boolean result = true;
    if (StringUtils.isEmpty(stringDate)) {
      result = false;
    } else if (!stringDate.matches(
        getView().context().getString(R.string.regexp_validate_simple_date))) {
      getView().errorDateIsInvalid();
      result = false;
    }
    return result;
  }

  @Override
  protected boolean verifyData(@NonNull final Sign sign, @NonNull final SignContract.View view) {
    boolean result = true;
    if (StringUtils.isEmpty(sign.fistname)) {
      view.errorFirstnameIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(sign.surname)) {
      view.errorSurnameIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(sign.phone)) {
      view.errorPhoneIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(sign.email)) {
      view.errorEmailIsEmpty();
      result = false;
    } else if (!sign.email.matches("[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z0-9._-]{2,3}")) {
      view.errorEmailIsInvalid();
      result = false;
    }
    if (StringUtils.isEmpty(sign.password)) {
      view.errorPasswordIsEmpty();
      result = false;
    } else if (StringUtils.isEmpty(sign.passwordConfirm) || !sign.password.equals(
        sign.passwordConfirm)) {
      view.errorPasswordsDontMatch();
      result = false;
    }
    if (sign.dateBirthday == 0L) {
      view.errorBirthdayIsEmpty();
      result = false;
    }
    if (StringUtils.isEmpty(sign.city)) {
      view.errorCityIsNotSelected();
      result = false;
    }
    return result;
  }

  private void onSuccess(final SignResponse response) {
  }

  private void onError(final Throwable throwable) {
    throwable.printStackTrace();
    getView().showError(throwable.getMessage());
  }
}

package ru.digitalwand.nonstopfit.ui.login.sign;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import icepick.State;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.di.component.SignActivityComponent;
import ru.digitalwand.nonstopfit.di.module.SignPresenterModule;
import ru.digitalwand.nonstopfit.ui.base.HasComponentBaseActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 04.03.2017 10:02.
 */
public class SignActivity extends HasComponentBaseActivity<SignActivityComponent>
    implements SignContract.View<Sign> {

  @BindView(R.id.til_firstname)
  protected TextInputLayout tilFirstname;
  @BindView(R.id.et_firstname)
  protected EditText etFirstname;

  @BindView(R.id.til_surname)
  protected TextInputLayout tilSurname;
  @BindView(R.id.et_surname)
  protected EditText etSurname;

  @BindView(R.id.til_phone)
  protected TextInputLayout tilPhone;
  @BindView(R.id.et_phone)
  protected EditText etPhone;

  @BindView(R.id.til_email)
  protected TextInputLayout tilEmail;
  @BindView(R.id.et_email)
  protected EditText etEmail;

  @BindView(R.id.til_password)
  protected TextInputLayout tilPassword;
  @BindView(R.id.et_password)
  protected EditText etPassword;

  @BindView(R.id.til_confirm_password)
  protected TextInputLayout tilPasswordConfirm;
  @BindView(R.id.et_password_confirm)
  protected EditText etPasswordConfirm;

  @BindView(R.id.til_birthday)
  protected TextInputLayout tilBirthday;
  @BindView(R.id.et_birthday)
  protected EditText etBirthday;

  @BindView(R.id.rg_gender)
  protected RadioGroup rgGender;

  @BindView(R.id.s_city)
  protected AppCompatSpinner sCity;

  @Inject
  protected SignPresenter presenter;
  @State
  protected Date birthday;
  @State
  protected Sign.Gender gender;
  @State
  protected String city;
  private boolean ready;

  @Override
  protected int getLayout() {
    return R.layout.activity_sign;
  }

  @Override
  protected void applyInject() {
    component().inject(this);
  }

  @Override
  public SignActivityComponent component() {
    return buildMainComponent().plus(new SignPresenterModule());
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.title_sign_up);
    super.initToolbar();
    setDisplayHome(true);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    rgGender.setOnCheckedChangeListener(this::onGenderSelect);
    initCitySpinner();
    ready = true;
    presenter.attachView(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override
  public Context context() {
    return this;
  }

  @Override
  public void showError(final String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showLoading() {
  }

  @Override
  public void hideLoading() {
  }

  @Override
  public boolean isReady() {
    return ready;
  }

  @Override
  public void onSignSuccsess() {

  }

  @Override
  public void errorFirstnameIsEmpty() {
    tilFirstname.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorSurnameIsEmpty() {
    tilSurname.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorPhoneIsEmpty() {
    tilPhone.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorEmailIsEmpty() {
    tilEmail.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorEmailIsInvalid() {
    tilEmail.setError(getString(R.string.error_email_is_not_valid));
  }

  @Override
  public void errorPasswordIsEmpty() {
    tilPassword.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorPasswordsDontMatch() {
    tilPassword.setError(getString(R.string.error_passwords_dont_match));
    tilPasswordConfirm.setError(getString(R.string.error_passwords_dont_match));
  }

  @Override
  public void errorBirthdayIsEmpty() {
    tilBirthday.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void errorCityIsNotSelected() {
    showError(getString(R.string.error_city_is_not_selected));
  }

  @Override
  public void errorDateIsInvalid() {
    tilBirthday.setError(getString(R.string.error_date_is_invalid));
  }

  @NonNull
  protected Sign getSign() {
    final long dateBirthday = birthday == null ? 0L : birthday.getTime();
    final String stringGender = gender == null ? null : gender.getTitle();
    return new Sign(etFirstname.getText().toString(), etSurname.getText().toString(),
                    etPhone.getText().toString(), etEmail.getText().toString(),
                    etPassword.getText().toString(), etPasswordConfirm.getText().toString(),
                    dateBirthday, stringGender, city);
  }

  @OnClick(R.id.b_sign_up)
  protected void onSignUpClick() {
    presenter.setData(getSign());
    presenter.setBirthdayDate(etBirthday.getText().toString());
    presenter.signUp();
  }

  @OnTextChanged(value = R.id.et_firstname, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onFirstnameChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilFirstname.setError(null);
    }
  }

  @OnTextChanged(value = R.id.et_surname, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onSurNameChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilSurname.setError(null);
    }
  }

  @OnTextChanged(value = R.id.et_phone, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onPhoneChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilPhone.setError(null);
    }
  }

  @OnTextChanged(value = { R.id.et_email }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onEmailChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilEmail.setError(null);
    }
  }

  @OnTextChanged(value = { R.id.et_password, R.id.et_password_confirm },
      callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onPasswordChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilPassword.setError(null);
      tilPasswordConfirm.setError(null);
    }
  }

  @OnTextChanged(value = { R.id.et_birthday }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onBirthdayChanged(final Editable editable) {
    final String date = editable.toString();
    if (date.length() == 10 && presenter.verifyBirthdayDate(date)) {
      tilBirthday.setError(null);
    }
  }

  @OnClick(R.id.et_birthday)
  protected void onDateClick() {
    onDateChanged(true);
  }

  @OnFocusChange(R.id.et_birthday)
  protected void onDateChanged(final boolean focused) {
    if (focused) {
      final Calendar c = Calendar.getInstance();
      final int year = c.get(Calendar.YEAR);
      final int month = c.get(Calendar.MONTH);
      final int day = c.get(Calendar.DAY_OF_MONTH);
      new DatePickerDialog(this, this::onDateSet, year, month, day).show();
    }
  }

  private void onDateSet(final DatePicker view, final int year, final int month, final int day) {
    final Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, day);
    birthday = c.getTime();
    etBirthday.setText(DateFormatUtils.format(c, "dd.MM.yyyy"));
  }

  private void onGenderSelect(final RadioGroup radioGroup, @IdRes final int checkedId) {
    switch (checkedId) {
      case R.id.arb_male:
        gender = Sign.Gender.MALE;
        break;
      case R.id.arb_female:
        gender = Sign.Gender.FEMALE;
        break;
      case R.id.arb_no_matter:
        gender = Sign.Gender.NO_MATTER;
        break;
    }
  }

  private void initCitySpinner() {
    final ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    sCity.setAdapter(adapter);
    sCity.setSelection(0);
  }

  @OnItemSelected(R.id.s_city)
  protected void onCitySelected(final android.widget.AdapterView<?> adapterView,
                                final int position) {
    city = position > 0 ? (String) adapterView.getAdapter().getItem(position) : null;
  }
}

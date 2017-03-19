package ru.digitalwand.nonstopfit.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.di.component.LoginActivityComponent;
import ru.digitalwand.nonstopfit.di.module.LoginPresenterModule;
import ru.digitalwand.nonstopfit.ui.base.HasComponentBaseActivity;
import ru.digitalwand.nonstopfit.ui.login.reset.ResetActivity;
import ru.digitalwand.nonstopfit.ui.login.sign.SignActivity;
import ru.digitalwand.nonstopfit.ui.login.sms.SmsApplyActivity;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 21:57.
 */
public class LoginActivity extends HasComponentBaseActivity<LoginActivityComponent>
    implements LoginContract.View<Login> {

  protected static final int REQUEST_CODE_OPEN_SIGN_UP = 100;
  protected static final int REQUEST_CODE_OPEN_RESET = 101;
  protected static final int REQUEST_CODE_OPEN_SMS_APPLY = 102;

  @BindView(R.id.sv_login_form)
  protected ScrollView svLoginForm;
  @BindView(R.id.til_login)
  protected TextInputLayout tilLogin;
  @BindView(R.id.et_login)
  protected EditText etLogin;
  @BindView(R.id.til_password)
  protected TextInputLayout tilPassword;
  @BindView(R.id.et_password)
  protected EditText etPassword;
  @BindView(R.id.b_enter)
  protected Button bEnter;
  @Inject
  protected LoginPresenter presenter;
  private boolean ready;

  @Override
  protected int getLayout() {
    return R.layout.activity_login;
  }

  @Override
  protected void applyInject() {
    component().inject(this);
  }

  @Override
  public LoginActivityComponent component() {
    return buildMainComponent().plus(new LoginPresenterModule());
  }

  @Override
  protected void initToolbar() {
    //На этом экране toolbar не нужен
    toolbar.setVisibility(View.GONE);
  }

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ready = true;
    presenter.attachView(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void startSmsApplyActivity() {
    startActivityForResult(new Intent(this, SmsApplyActivity.class), REQUEST_CODE_OPEN_SMS_APPLY);
  }

  @Override
  public void emailIsEmpty() {
    tilLogin.setErrorEnabled(true);
    tilLogin.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void emailIsInvalid() {
    tilLogin.setErrorEnabled(true);
    tilLogin.setError(getString(R.string.error_email_is_not_valid));
  }

  @Override
  public void passwordIsEmpty() {
    tilLogin.setErrorEnabled(true);
    tilPassword.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void setButtonEnterEnable(final boolean result) {
    bEnter.setEnabled(result);
  }

  @Override
  public Context context() {
    return this;
  }

  @Override
  public void showError(final String message) {
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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

  @OnClick(value = { R.id.et_login, R.id.et_password })
  protected void onClickEtFields() {
    onFocusEtFields(true);
  }

  @OnFocusChange(value = { R.id.et_login, R.id.et_password })
  protected void onFocusEtFields(final boolean focus) {
    if (focus) {
      svLoginForm.postDelayed(() -> svLoginForm.smoothScrollTo(0, etPassword.getBottom()), 300L);
    }
  }

  @OnClick(R.id.b_enter)
  protected void onLoginClick() {
    presenter.setData(getLogin());
    presenter.login();
  }

  @OnLongClick(R.id.b_sign_up)
  protected boolean stub() {
    startActivityForResult(new Intent(this, SmsApplyActivity.class), REQUEST_CODE_OPEN_SMS_APPLY);
    return true;
  }

  @OnClick(R.id.b_sign_up)
  protected void onSignUpClick() {
    Intent intent = new Intent(this, SignActivity.class);
    startActivityForResult(intent, REQUEST_CODE_OPEN_SIGN_UP);
  }

  @OnClick(R.id.b_reset_password)
  protected void onResetPasswordClick() {
    Intent intent = new Intent(this, ResetActivity.class);
    startActivityForResult(intent, REQUEST_CODE_OPEN_SIGN_UP);
  }

  @OnEditorAction(R.id.et_password)
  protected boolean onDoneAction(int actionId) {
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      onLoginClick();
      return true;
    }
    return false;
  }

  @OnTextChanged(value = R.id.et_login, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onLoginChanged(final Editable editable) {
    if (StringUtils.isNotEmpty(editable)) {
      tilLogin.setError(null);
      tilLogin.setErrorEnabled(false);
    }
  }

  @OnTextChanged(value = R.id.et_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onPasswordChanged(final Editable editable) {
    if (StringUtils.isNotEmpty(editable)) {
      tilPassword.setError(null);
      tilPassword.setErrorEnabled(false);
      presenter.verifyPassword(editable.toString());
    }
  }

  @NonNull
  protected Login getLogin() {
    return new Login(etLogin.getText().toString(), etPassword.getText().toString());
  }
}

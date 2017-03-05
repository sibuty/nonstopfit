package ru.digitalwand.nonstopfit.ui.login.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Sign;
import ru.digitalwand.nonstopfit.data.entity.SignResponse;
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

  @BindView(R.id.til_login)
  protected TextInputLayout tilLogin;
  @BindView(R.id.et_login)
  protected EditText etLogin;
  @BindView(R.id.til_password)
  protected TextInputLayout tilPassword;
  @BindView(R.id.et_password)
  protected EditText etPassword;
  @BindView(R.id.til_confirm_password)
  protected TextInputLayout tilConfirmPassword;
  @BindView(R.id.et_confirm_password)
  protected EditText etConfirmPassword;
  @BindView(R.id.til_email)
  protected TextInputLayout tilEmail;
  @BindView(R.id.et_email)
  protected EditText etEmail;
  @Inject
  protected SignPresenter presenter;
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
  public void signSucsess(SignResponse signResponse) {

  }

  @Override
  public void loginIsEmpty() {
    tilLogin.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void passwordIsEmpty() {
    tilPassword.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void passwordsDontMatch() {
    tilPassword.setError(getString(R.string.error_passwords_dont_match));
    tilConfirmPassword.setError(getString(R.string.error_passwords_dont_match));
  }

  @Override
  public void emailIsEmpty() {
    tilEmail.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void emailIsInvalid() {
    tilEmail.setError(getString(R.string.error_email_is_not_valid));
  }

  @NonNull
  protected Sign getSign() {
    return new Sign(etLogin.getText().toString(), etPassword.getText().toString(),
                    etConfirmPassword.getText().toString(), etEmail.getText().toString());
  }

  @OnClick(R.id.b_sign_up)
  protected void onSignUpClick() {
    presenter.setData(getSign());
    presenter.signUp();
  }

  @OnEditorAction(R.id.et_email)
  protected boolean onDoneAction(int actionId) {
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      onSignUpClick();
      return true;
    }
    return false;
  }

  @OnTextChanged(value = R.id.et_login, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onLoginChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilLogin.setError(null);
    }
  }

  @OnTextChanged(value = { R.id.et_password, R.id.et_confirm_password },
      callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onPasswordChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilPassword.setError(null);
      tilConfirmPassword.setError(null);
    }
  }

  @OnTextChanged(value = { R.id.et_email }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onEmailChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilEmail.setError(null);
    }
  }
}

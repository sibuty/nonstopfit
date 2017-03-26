package ru.digitalwand.nonstopfit.ui.login.reset;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import icepick.State;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.User;
import ru.digitalwand.nonstopfit.di.component.ResetActivityComponent;
import ru.digitalwand.nonstopfit.di.module.ResetPresenterModule;
import ru.digitalwand.nonstopfit.ui.base.HasComponentBaseActivity;
import ru.digitalwand.nonstopfit.util.ProgressDialogHelper;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 23:45.
 */
public class ResetActivity extends HasComponentBaseActivity<ResetActivityComponent>
    implements ResetContract.View<User> {

  @BindView(R.id.til_field)
  protected TextInputLayout tilField;
  @BindView(R.id.et_field)
  protected EditText etField;
  @BindView(R.id.ll_reset_form)
  protected View llResetForm;
  @BindView(R.id.ll_reset_form_success)
  protected View llResetFormSucess;
  @Inject
  protected ResetPresenter presenter;
  @Inject
  protected ProgressDialogHelper progressDialogHelper;
  @State
  protected boolean isResetSuccess;
  private boolean ready;

  @Override
  public ResetActivityComponent component() {
    return buildMainComponent().plus(new ResetPresenterModule());
  }

  @Override
  protected int getLayout() {
    return R.layout.activity_reset;
  }

  @Override
  protected void applyInject() {
    component().inject(this);
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.title_reset);
    super.initToolbar();
    setDisplayHome(true);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    chooseResetForm(isResetSuccess);
    ready = true;
    presenter.attachView(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    progressDialogHelper.onDestroy();
    presenter.detachView();
  }

  @Override
  public Context context() {
    return this;
  }

  @Override
  public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showLoading() {
    progressDialogHelper.show();
  }

  @Override
  public void hideLoading() {
    progressDialogHelper.hide();
  }

  @Override
  public boolean isReady() {
    return ready;
  }

  @Override
  public void showResetSuccessForm() {
    this.isResetSuccess = true;
    chooseResetForm(true);
  }

  @Override
  public void fieldIsEmpty() {
    tilField.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void emailIsInvalid() {
    tilField.setError(getString(R.string.error_email_is_not_valid));
  }

  @NonNull
  public User getUser() {
    final String fieldValue = etField.getText().toString();
    return fieldValue.contains("@") ? new User(null, fieldValue) : new User(fieldValue, null);
  }

  private void chooseResetForm(final boolean success) {
    llResetForm.setVisibility(success ? View.GONE : View.VISIBLE);
    llResetFormSucess.setVisibility(success ? View.VISIBLE : View.GONE);
  }

  @OnClick(R.id.b_send_password)
  protected void onSendPasswordClick() {
    presenter.setData(getUser());
    presenter.sendPassword();
  }

  @OnClick(R.id.b_after_reset_success)
  protected void onAfterResetSuccess() {
    finish();
  }

  @OnEditorAction(R.id.et_field)
  protected boolean onDoneAction(final int actionId) {
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      onSendPasswordClick();
      return true;
    }
    return false;
  }

  @OnTextChanged(value = R.id.et_field, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onFieldChaged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilField.setError(null);
    }
  }
}

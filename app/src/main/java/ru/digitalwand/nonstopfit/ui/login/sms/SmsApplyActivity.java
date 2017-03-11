package ru.digitalwand.nonstopfit.ui.login.sms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import ru.digitalwand.nonstopfit.di.component.SmsApplyComponent;
import ru.digitalwand.nonstopfit.di.module.SmsApplyPresenterModule;
import ru.digitalwand.nonstopfit.ui.base.HasComponentBaseActivity;

public class SmsApplyActivity extends HasComponentBaseActivity<SmsApplyComponent>
    implements SmsApplyContract.View<String> {

  public static final String EXTRA_RESULT = "extra_result";

  @BindView(R.id.til_sms_code)
  protected TextInputLayout tilSmsCode;
  @BindView(R.id.et_sms_code)
  protected EditText etSmsCode;
  @Inject
  protected SmsApplyPresenter presenter;
  private boolean ready;

  @Override
  protected int getLayout() {
    return R.layout.activity_sms_apply;
  }

  @Override
  public SmsApplyComponent component() {
    return buildMainComponent().plus(new SmsApplyPresenterModule());
  }

  @Override
  protected void applyInject() {
    component().inject(this);
  }

  @Override
  protected void initToolbar() {
    getToolbar().setTitle(R.string.title_sms_apply);
    super.initToolbar();
    setDisplayHome(true);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ready = true;
    presenter.attachView(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.registerSmsCodeReceiver();
  }

  @Override
  protected void onResume() {
    presenter.fillSmsCodeField();
    super.onResume();
  }

  @Override
  protected void onDestroy() {
    presenter.unregisterSmsCodeReceiver();
    super.onDestroy();
    presenter.detachView();
  }

  @Override
  public Context context() {
    return getApplicationContext();
  }

  @Override
  public void showError(String message) {
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
  public void showEmptySmsCodeError() {
    tilSmsCode.setError(getString(R.string.error_field_is_empty));
  }

  @Override
  public void smsApplySuccess() {
    final Intent result = new Intent();
    result.putExtra(EXTRA_RESULT, presenter.getData());
    setResult(RESULT_OK, result);
  }

  private String getSmsCode() {
    return etSmsCode.getText().toString();
  }

  @Override
  public void setSmsCode(final String smsCode) {
    etSmsCode.setText(smsCode);
  }

  @OnClick(R.id.b_apply)
  protected void onApplyClick() {
    presenter.setData(getSmsCode());
    presenter.applyCode();
  }

  @OnEditorAction(R.id.et_sms_code)
  protected boolean onDoneAction(int actionId) {
    if (actionId == EditorInfo.IME_ACTION_DONE) {
      onApplyClick();
      return true;
    }
    return false;
  }

  @OnTextChanged(value = R.id.et_sms_code, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  protected void onLoginChanged(final Editable editable) {
    if (StringUtils.isNoneEmpty(editable)) {
      tilSmsCode.setError(null);
    }
  }
}

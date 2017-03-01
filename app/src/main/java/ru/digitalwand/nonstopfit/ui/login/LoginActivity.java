package ru.digitalwand.nonstopfit.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.Login;
import ru.digitalwand.nonstopfit.data.entity.LoginResponse;
import ru.digitalwand.nonstopfit.data.wrapper.LoginWrapper;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 21:57.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

  @BindView(R.id.til_login)
  protected TextInputLayout tilLogin;
  @BindView(R.id.et_login)
  protected EditText etLogin;
  @BindView(R.id.til_password)
  protected TextInputLayout tilPassword;
  @BindView(R.id.et_password)
  protected EditText etPassword;

  private LoginContract.Presenter presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    presenter = new LoginPresenter(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    presenter.unsubscribe();
  }

  @Override
  public void loginSuccess(@NonNull LoginResponse loginResponse) {
  }

  @Override
  public void loginError(@NonNull final String message) {
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void setPresenter(final LoginContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @OnClick(R.id.b_enter)
  protected void onLoginClick() {
    final Login login = getLogin();
    if (login != null) {
      presenter.setLogin(login);
      presenter.subscribe();
    }
  }

  @Nullable
  protected Login getLogin() {
    final String login = etLogin.getText().toString();
    if (StringUtils.isBlank(login)) {
      tilLogin.setError(getString(R.string.error_field_is_empty));
      return null;
    }
    final String password = etPassword.getText().toString();
    if (StringUtils.isBlank(password)) {
      tilPassword.setError(getString(R.string.error_field_is_empty));
      return null;
    }
    return new Login(login, password);
  }
}

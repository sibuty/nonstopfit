package ru.digitalwand.nonstopfit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

  private static final long TIME_WAIT_STUB = 0L;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      Thread.sleep(TIME_WAIT_STUB);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    setContentView(R.layout.activity_splash);
  }

  @Override
  protected void onResume() {
    super.onResume();
    new Handler().postDelayed(this::afterSplash, TIME_WAIT_STUB);
  }

  private void afterSplash() {
    startActivity(new Intent(this, LoginActivity.class));
    finish();
  }
}

package ru.digitalwand.nonstopfit.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import ru.digitalwand.nonstopfit.R;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 05.03.2017 19:59.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @BindView(R.id.toolbar)
  protected Toolbar toolbar;

  @LayoutRes
  protected abstract int getLayout();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    ButterKnife.bind(this);
    initToolbar();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  protected Toolbar getToolbar() {
    return toolbar;
  }

  protected void initToolbar() {
    toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
    setSupportActionBar(toolbar);
  }

  protected void setDisplayHome(final boolean enable) {
    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(enable);
      actionBar.setDisplayShowHomeEnabled(enable);
    }
  }

  private int getStatusBarHeight() {
    int result = 0;
    final int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }
}

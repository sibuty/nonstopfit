package ru.digitalwand.nonstopfit.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
    setSupportActionBar(toolbar);
  }
}

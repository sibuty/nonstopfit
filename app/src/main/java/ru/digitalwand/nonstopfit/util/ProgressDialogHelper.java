package ru.digitalwand.nonstopfit.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

import ru.digitalwand.nonstopfit.R;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 25.03.2017 17:57.
 */
public class ProgressDialogHelper {

  @StyleRes
  private int themeId = 0;
  private ProgressDialog progressDialog;

  public ProgressDialogHelper() {
    this(R.style.ProgressDialog);
  }

  public ProgressDialogHelper(@StyleRes final int themeId) {
    this.themeId = themeId;
  }

  public ProgressDialog getProgressDialog() {
    return progressDialog;
  }

  public void setProgressDialog(final ProgressDialog progressDialog) {
    onDestroy();
    this.progressDialog = progressDialog;
  }

  public void create(@NonNull final Context context, @StringRes final int message) {
    create(context, context.getString(message));
  }

  public void create(@NonNull final Context context,
                     @StringRes final int title,
                     @StringRes final int message) {
    create(context, context.getString(title), context.getString(message));
  }

  public void create(@NonNull final Context context, @Nullable final CharSequence message) {
    create(context, null, message, true, true, null);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message) {
    create(context, title, message, true, true, null);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message,
                     final boolean cancelable,
                     @Nullable final DialogInterface.OnCancelListener onCancelListener) {
    create(context, title, message, true, cancelable, onCancelListener);
  }

  public void create(@NonNull final Context context,
                     @Nullable final CharSequence title,
                     @Nullable final CharSequence message,
                     final boolean indeterminate,
                     final boolean cancelable,
                     @Nullable final DialogInterface.OnCancelListener onCancelListener) {
    onDestroy();
    progressDialog = new ProgressDialog(context, themeId);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setTitle(title);
    progressDialog.setMessage(message);
    progressDialog.setIndeterminate(indeterminate);
    progressDialog.setCancelable(cancelable);
    progressDialog.setOnCancelListener(onCancelListener);
  }

  public void show() {
    if (progressDialog != null) {
      progressDialog.show();
    }
  }

  public void hide() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }

  public void onDestroy() {
    hide();
    progressDialog = null;
  }
}

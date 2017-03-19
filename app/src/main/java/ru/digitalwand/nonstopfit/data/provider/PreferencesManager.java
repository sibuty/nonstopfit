package ru.digitalwand.nonstopfit.data.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

import ru.digitalwand.nonstopfit.App;
import ru.digitalwand.nonstopfit.R;
import ru.digitalwand.nonstopfit.data.entity.AccessToken;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 19.03.2017 20:50.
 */
public class PreferencesManager {

  private static final String KEY_ACCESS_TOKEN = "access_token";

  @NonNull
  private final App app;
  @NonNull
  private final SharedPreferences sharedPreferences;
  @NonNull
  private final String fileKeyPreference;

  public PreferencesManager(@NonNull final App app) {
    this.app = app;
    this.fileKeyPreference =
        app.getString(R.string.file_key_shared_preference_app, app.getPackageName());
    this.sharedPreferences = app.getSharedPreferences(fileKeyPreference, Context.MODE_PRIVATE);
  }

  @NonNull
  public SharedPreferences getSharedPreferences() {
    return sharedPreferences;
  }

  @NonNull
  public SharedPreferences getSharedPreferences(@NonNull final String fileKey) {
    return this.app.getSharedPreferences(getFileKey(fileKey), Context.MODE_PRIVATE);
  }

  @NonNull
  public SharedPreferences getSharedPreferences(@StringRes final int fileKey) {
    return this.app.getSharedPreferences(getFileKey(app.getString(fileKey)), Context.MODE_PRIVATE);
  }

  public void putString(@NonNull final String key, @Nullable final String value) {
    this.sharedPreferences.edit().putString(key, value).apply();
  }

  @Nullable
  public AccessToken getAccessToken() {
    try {
      return AccessToken.fromJson(sharedPreferences.getString(KEY_ACCESS_TOKEN, ""));
    } catch (final IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void setAccessToken(@NonNull final AccessToken accessToken) {
    try {
      putString(KEY_ACCESS_TOKEN, accessToken.toJson());
    } catch (final JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private String getFileKey(@NonNull final String fileKey) {
    return String.format("%s.%s", this.app.getPackageName(), fileKey);
  }
}

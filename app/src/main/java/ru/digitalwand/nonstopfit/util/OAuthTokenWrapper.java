package ru.digitalwand.nonstopfit.util;

import android.support.annotation.NonNull;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 19.03.2017 20:32.
 */
public class OAuthTokenWrapper {

  public static String bearer(@NonNull final String token) {
    return "Bearer " + token;
  }
}

package ru.digitalwand.nonstopfit.data.entity;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 19.03.2017 21:02.
 *
 * Пример
 * {
 * "access_token":"5ExV5QaSesMCw6O3R7WvBlABwa0r28",
 * "token_type":"Bearer",
 * "expires_in":36000,
 * "refresh_token":"ZjvGwWrQhBewRWCqGP3iM7zF8ZHqNh",
 * "scope":"read write groups"
 * }
 */
public class AccessToken {

  @NonNull
  private String accessToken;
  @NonNull
  private String tokenType;
  @NonNull
  private long expiresIn;
  @NonNull
  private String refreshToken;
  @NonNull
  private String scope;

  @JsonCreator
  public AccessToken(@NonNull @JsonProperty("access_token") final String accessToken,
                     @NonNull @JsonProperty("token_type") final String tokenType,
                     @JsonProperty("expires_in") final long expiresIn,
                     @NonNull @JsonProperty("refresh_token") final String refreshToken,
                     @NonNull @JsonProperty("scope") final String scope) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.expiresIn = expiresIn;
    this.refreshToken = refreshToken;
    this.scope = scope;
  }

  private static String toJson(@NonNull final AccessToken accessToken)
      throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(accessToken);
  }

  public static AccessToken fromJson(@NonNull final String json) throws IOException {
    return new ObjectMapper().readValue(json, AccessToken.class);
  }

  public String toJson() throws JsonProcessingException {
    return toJson(this);
  }
}

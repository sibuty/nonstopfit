package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:44.
 */
public class LoginResponse {

  @JsonProperty("access_token")
  public String accessToken;
  @JsonProperty("token_type")
  public String tokenType;
  @JsonProperty("expires_in")
  public long expiresIn;
  @JsonProperty("refresh_token")
  public String refreshToken;
  @JsonProperty("scope")
  public String scope;
}

package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:44.
 */
public class Login {

  public static final String GRANT_PASSWORD = "password";

  @JsonProperty("grant_type")
  public String grantType;
  //логин пользователя
  @JsonProperty("username")
  public String userName;
  //пароль пользователя
  @JsonProperty("password")
  public String password;

  public Login(final String userName, final String password) {
    this.grantType = GRANT_PASSWORD;
    this.userName = userName;
    this.password = password;
  }
}

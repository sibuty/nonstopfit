package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:07.
 */
public class Sign {

  @JsonProperty("username")
  public String userName;
  // md5-хэш контантенации пароля
  @JsonProperty("password")
  public String password;
  // повторение пароля
  @JsonProperty("password_confirm")
  public String passwordConfirm;
  @JsonProperty("email")
  public String email;

  public Sign(String userName, String password, String passwordConfirm, String email) {
    this.userName = userName;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.email = email;
  }
}

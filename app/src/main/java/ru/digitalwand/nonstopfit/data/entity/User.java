package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:56.
 */
public class User {

  //логин пользователя
  @JsonProperty("username")
  public String userName;
  //пароль пользователя
  @JsonProperty("email")
  public String email;

  public User(String userName, String email) {
    this.userName = userName;
    this.email = email;
  }
}

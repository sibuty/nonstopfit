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
  @JsonProperty("password")
  public String password;

}

package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:03.
 */
public class SignResponse {

  @JsonProperty("username")
  public String userName;
  @JsonProperty("email")
  public String email;
  @JsonProperty("password")
  public String password;
}

package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:03.
 */
public class SignResponse {

  @JsonProperty("first_name")
  public String fistname;
  @JsonProperty("last_name")
  public String surname;
  @JsonProperty("phone")
  public String phone;
  @JsonProperty("email")
  public String email;
  @JsonProperty("password")
  public String password;
  @JsonProperty("date_birthday")
  public long dateBirthday;
  @JsonProperty("sex")
  public int gender;
}

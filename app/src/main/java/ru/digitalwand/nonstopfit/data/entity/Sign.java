package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:07.
 */
public class Sign {

  @JsonProperty("username")
  public String fistname;
  @JsonProperty("surname")
  public String surname;
  @JsonProperty("phone")
  public String phone;
  @JsonProperty("email")
  public String email;
  @JsonProperty("password")
  public String password;
  @JsonProperty("password_confirm")
  public String passwordConfirm;
  @JsonProperty("date_birthday")
  public long dateBirthday;
  @JsonProperty("gender")
  public String gender;
  @JsonProperty("city")
  public String city;

  public Sign(String fistname,
              String surname,
              String phone,
              String email,
              String password,
              String passwordConfirm,
              long dateBirthday,
              String gender,
              String city) {
    this.fistname = fistname;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.dateBirthday = dateBirthday;
    this.gender = gender;
    this.city = city;
  }

  public enum Gender {
    MALE("Мужчина"), FEMALE("Женища"), NO_MATTER("Не важно");

    private final String title;

    Gender(final String title) {
      this.title = title;
    }

    public String getTitle() {
      return title;
    }
  }
}

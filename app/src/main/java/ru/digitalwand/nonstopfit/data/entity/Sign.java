package ru.digitalwand.nonstopfit.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 28.02.2017 22:07.
 *
 * {
 * "first_name": "first_name",
 * "last_name": "last_name",
 * "email": "email",
 * "phone": "phone",
 * "password": "password/md5",
 * "password_confirm": "password/md5",
 * "sex": 0(Male) or 1(Female)
 * }
 */
public class Sign {

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
  @JsonProperty("password_confirm")
  public String passwordConfirm;
  @JsonProperty("date_birthday")
  public long dateBirthday;
  @JsonProperty("sex")
  public int gender;
  @JsonProperty("city")
  public String city;

  public Sign(String fistname,
              String surname,
              String phone,
              String email,
              String password,
              String passwordConfirm,
              long dateBirthday,
              int gender,
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
    MALE(0, "Мужчина"), FEMALE(1, "Женщина"), NO_MATTER(-1, "Не важно");

    private final int id;
    private final String title;

    Gender(final int id, final String title) {
      this.id = id;
      this.title = title;
    }

    public int getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }
  }
}

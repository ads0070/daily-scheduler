package kr.ac.deu.cse.scheduler.user.domain;

import lombok.Value;

@Value
public class UserRequest {

  String username;
  String password;
  String firstName;
  String lastName;
  String phoneNumber;
  String eMail;
}

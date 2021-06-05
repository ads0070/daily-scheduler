package kr.ac.deu.cse.scheduler.user.domain;

public interface User {

  String getUsername();

  String getPassword();

  String getFirstName();

  String getLastName();

  String getPhoneNumber();

  String getEMail();

  void setState(UserState state);

  UserState getState();
}

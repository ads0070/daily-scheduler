package kr.ac.deu.cse.scheduler.user.domain;

public class CommonUser implements User {

  String username;
  String password;

  public CommonUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }
}

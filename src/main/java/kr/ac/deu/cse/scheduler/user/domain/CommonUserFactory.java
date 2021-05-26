package kr.ac.deu.cse.scheduler.user.domain;

public class CommonUserFactory implements UserFactory {

  @Override
  public User create(String username, String password) {
    return new CommonUser(username, password);
  }
}

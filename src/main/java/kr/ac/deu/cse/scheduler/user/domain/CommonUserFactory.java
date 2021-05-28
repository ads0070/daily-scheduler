package kr.ac.deu.cse.scheduler.user.domain;

public class CommonUserFactory implements UserFactory {

  @Override
  public User create(String username, String password, String firstName, String lastName, String phoneNumber, String eMail) {
    return new CommonUser(username, password, firstName, lastName, phoneNumber, eMail);
  }
}

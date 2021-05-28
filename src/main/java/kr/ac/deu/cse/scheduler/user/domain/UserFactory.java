package kr.ac.deu.cse.scheduler.user.domain;

public interface UserFactory {

  User create(String username, String password, String firstName, String lastName, String phoneNumber, String eMail);
}

package kr.ac.deu.cse.scheduler.user.domain;

public class CommonUser implements User {

  String username;
  String password;
  String firstName;
  String lastName;
  String phoneNumber;
  String eMail;

  UserState state;

  public CommonUser(String username, String password, String firstName, String lastName, String phoneNumber, String eMail) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.eMail = eMail; 
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public String getFirstName() {
    return firstName;
  }
  
  @Override
  public String getLastName() {
    return lastName;
  }
  
  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  @Override
  public String getEMail() {
    return eMail;
  }

  @Override
  public void setState(UserState state) {
    this.state = state;
  }
}

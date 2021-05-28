package kr.ac.deu.cse.scheduler.user.domain;

public class UserDtoRequestAdapter implements UserDtoAdapter<User, UserDataMapper> {

  @Override
  public UserDataMapper getEntity(User user) {
    return UserDataMapper.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .phoneNumber(user.getPhoneNumber())
      .eMail(user.getEMail())
      .build();
  }
}

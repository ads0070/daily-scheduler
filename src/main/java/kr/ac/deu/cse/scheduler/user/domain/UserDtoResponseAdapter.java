package kr.ac.deu.cse.scheduler.user.domain;

public class UserDtoResponseAdapter implements UserDtoAdapter<UserDataMapper, UserResponse> {

  @Override
  public UserResponse getEntity(UserDataMapper user) {
    return new UserResponse(user.getId(), user.getUsername(),
    						user.getFirstName(), user.getLastName(),
    						user.getPhoneNumber(), user.getEMail());
  }
}

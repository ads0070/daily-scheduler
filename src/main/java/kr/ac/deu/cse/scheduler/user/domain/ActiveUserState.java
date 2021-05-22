package kr.ac.deu.cse.scheduler.user.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActiveUserState extends UserState {

  public ActiveUserState() {
    this.active = true;
  }

  @Override
  public UserResponse getUserEntity(User user) {
    return UserResponse.builder()
      .id(user.getId())
      .username(user.getUsername())
      .isActive(true)
      .build();
  }

  @Override
  public void onActivate(User user) {
    log.info("이미 활성화된 유저");
  }

  @Override
  public void onDeactivate(User user) {
    user.setUserState(new DeactivatedUserState());
  }
}

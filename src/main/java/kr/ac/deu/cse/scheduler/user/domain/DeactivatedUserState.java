package kr.ac.deu.cse.scheduler.user.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeactivatedUserState extends UserState {

  public DeactivatedUserState() {
    this.active = false;
  }

  @Override
  public UserResponse getUserEntity(User user) {
    return UserResponse.builder()
      .id(user.getId())
      .isActive(false)
      .build();
  }

  @Override
  public void onActivate(User user) {
    user.setUserState(new ActiveUserState());
  }

  @Override
  public void onDeactivate(User user) {
    log.info("이미 비활성화된 유저");
  }
}

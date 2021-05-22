package kr.ac.deu.cse.scheduler.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class UserState {

  @Column
  protected boolean active;

  public void onActivate(User user) {
    this.active = true;
  }

  public void onDeactivate(User user) {
    this.active = false;
  }

  public UserResponse getUserEntity(User user) {
    return this.active
      ? new ActiveUserState().getUserEntity(user)
      : new DeactivatedUserState().getUserEntity(user);
  }
}

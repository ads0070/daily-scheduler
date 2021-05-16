package kr.ac.deu.cse.scheduler.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserState {

  @Column
  private boolean active;

  public UserState() {
  }

  public UserState(boolean active) {
    this.active = active;
  }

  public void toggleActiveState(User user) {
    this.active = !user.getStatus().isActive();
  }

  public boolean isActive() {
    return active;
  }
}

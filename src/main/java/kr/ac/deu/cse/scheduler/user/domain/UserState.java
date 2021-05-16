package kr.ac.deu.cse.scheduler.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserState {
  // TODO: improve class

  @Column
  private boolean active;

  public UserState() {
  }

  public UserState(boolean active) {
    this.active = active;
  }

  public boolean isActive() {
    return active;
  }
}

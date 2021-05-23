package kr.ac.deu.cse.scheduler.user.domain;

public class UserStateFactory {

  public UserState getState(boolean state) {
    if (state) {
      return new ActiveUserState();
    } else {
      return new DeactivatedUserState();
    }
  }
}

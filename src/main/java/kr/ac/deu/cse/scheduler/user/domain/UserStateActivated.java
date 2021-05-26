package kr.ac.deu.cse.scheduler.user.domain;

public class UserStateActivated implements UserState {

  @Override
  public void onActivate(User user) {
  }

  @Override
  public void onDeactivate(User user) {
    user.setState(new UserStateDeactivated());
  }
}

package kr.ac.deu.cse.scheduler.user.domain;

public class UserStateDeactivated implements UserState {

  @Override
  public void onActivate(User user) {
    user.setState(new UserStateActivated());
  }

  @Override
  public void onDeactivate(User user) {
  }
}

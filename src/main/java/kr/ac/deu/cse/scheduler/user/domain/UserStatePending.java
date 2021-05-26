package kr.ac.deu.cse.scheduler.user.domain;

public class UserStatePending implements UserState {

  @Override
  public void onActivate(User user) {
    user.setState(new UserStateActivated());
  }

  @Override
  public void onDeactivate(User user) {
    user.setState(new UserStateDeactivated());
  }
}

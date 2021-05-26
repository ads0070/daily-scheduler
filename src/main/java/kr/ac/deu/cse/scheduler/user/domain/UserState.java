package kr.ac.deu.cse.scheduler.user.domain;

public interface UserState {

  void onActivate(User user);

  void onDeactivate(User user);
}

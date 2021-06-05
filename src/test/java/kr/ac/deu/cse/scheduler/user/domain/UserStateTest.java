package kr.ac.deu.cse.scheduler.user.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserStateTest {

  UserFactory factory = new CommonUserFactory();
  User user;

  @BeforeEach
  void setUp() {
    user = factory.create(
      "testUser",
      "password",
      "first",
      "last",
      "010-0000-0000",
      "test@example.org"
    );
  }

  @Test
  void firstCreatedUserStateIsPending() {
    assertTrue(user.getState() instanceof UserStatePending);
  }

  @Test
  void changeUserStateActivated() {
    user.getState().onActivate(user);

    assertTrue(user.getState() instanceof UserStateActivated);
  }

  @Test
  void changeUserStateDeactivated() {
    user.getState().onDeactivate(user);

    assertTrue(user.getState() instanceof UserStateDeactivated);
  }
}

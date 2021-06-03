package kr.ac.deu.cse.scheduler.user.application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kr.ac.deu.cse.scheduler.user.domain.UserRequest;
import kr.ac.deu.cse.scheduler.user.domain.UserResponse;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest {

  private final UserService service;

  @Autowired
  public UserServiceTest(UserService service) {
    this.service = service;
  }

  @Test
  @Order(1)
  void createUserSuccess() {
    UserRequest request = new UserRequest("test", "password", "john", "doe", "010-0000-0000",
      "john@example.com");

    UserResponse response = service.createUser(request);

    assertNotNull(response);
  }

  @Test
  @Order(2)
  void createUserDuplicated() {
    UserRequest request = new UserRequest("test", "password", "john", "doe", "010-0000-0000",
      "john@example.com");

    assertThrows(Exception.class, () -> {
      UserResponse response = service.createUser(request);

      assertNotNull(response);
    });
  }

  @Test
  @Order(3)
  void findAllUsers() {
    List<?> users = service.getUsers();

    assertTrue(users.size() > 0);
  }

  @Test
  @Order(4)
  void findUserById() {
    List<UserResponse> users = service.getUsers();

    UserResponse user = service.getUserById(users.get(0).getId());

    assertEquals(users.get(0), user);
  }

  @Test
  @Order(5)
  void deleteUser() {
    List<UserResponse> users = service.getUsers();

    service.deleteUserById(users.get(0).getId());

    assertThrows(Exception.class, () -> {
      service.getUserById(users.get(0).getId());
    });
  }
}

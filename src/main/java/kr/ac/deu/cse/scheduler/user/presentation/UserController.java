package kr.ac.deu.cse.scheduler.user.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.user.application.UserService;
import kr.ac.deu.cse.scheduler.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/users")
@RepositoryRestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @ResponseBody
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @ResponseBody
  @GetMapping("/{id}")
  public User getOneUser(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @ResponseBody
  @PatchMapping("/{id}")
  public User updateUser(@PathVariable UUID id, @RequestBody User user) {
    return userService.updateUserById(id, user);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable UUID id) {
    userService.deleteUserById(id);
  }
}

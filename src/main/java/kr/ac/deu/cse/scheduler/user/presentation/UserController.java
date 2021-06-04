package kr.ac.deu.cse.scheduler.user.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.interfaces.AbstractController;
import kr.ac.deu.cse.scheduler.user.application.UserService;
import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.domain.UserRequest;
import kr.ac.deu.cse.scheduler.user.domain.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import io.jsonwebtoken.io.IOException;

@RequestMapping("/users")
@RepositoryRestController
public class UserController extends AbstractController<UserRequest, UUID> {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @ResponseBody
  @PostMapping
  public ResponseEntity<?> create(@RequestBody final UserRequest request) {
    return ResponseEntity.ok(service.createUser(request));
  }

  @ResponseBody
  @GetMapping
  public ResponseEntity<?> readAll() {
    List<UserResponse> response = service.getUsers();

    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<?> readOne(@PathVariable final UUID id) {
    UserResponse response = service.getUserById(id);

    return ResponseEntity.ok(response);
  }
  
  @ResponseBody
  @GetMapping("/find/{username}")
  public ResponseEntity<?> readOne(@PathVariable final String username) {
    UUID id = service.getUserByUsername(username);
    return ResponseEntity.ok(id);
  }
  
  @ResponseBody
  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody final UserRequest request) {
    boolean auth = service.authenticate(request.getUsername(), request.getPassword());
    return ResponseEntity.ok(auth);
  }

  @ResponseBody
  @PostMapping("/patch/{id}")
  public ResponseEntity<?> update(@PathVariable final UUID id,
    @RequestBody final UserRequest request) {
    UserResponse response = service.updateUserById(id, request);

    return ResponseEntity.ok(response);
  }
  
  @ResponseBody
  @PostMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable final UUID id) {
    service.deleteUserById(id);

    return ResponseEntity.noContent().build();
  }
}

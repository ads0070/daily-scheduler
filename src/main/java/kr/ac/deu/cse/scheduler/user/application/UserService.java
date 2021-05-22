package kr.ac.deu.cse.scheduler.user.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import kr.ac.deu.cse.scheduler.user.domain.ActiveUserState;
import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.domain.UserRequest;
import kr.ac.deu.cse.scheduler.user.domain.UserResponse;
import kr.ac.deu.cse.scheduler.user.infrastructure.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserJpaRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserJpaRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserResponse createUser(final UserRequest newUser) {
    String hashedPassword = passwordEncoder.encode(newUser.getPassword());
    User user = User.builder()
      .username(newUser.getUsername())
      .password(hashedPassword)
      .state(new ActiveUserState())
      .build();

    user = repository.save(user);

    return user.getUserEntity();
  }

  public List<UserResponse> retrieveUsers() {
    return repository.findAll().stream()
      .map(User::getUserEntity)
      .collect(Collectors.toList());
  }

  public UserResponse retrieveUserById(UUID id) {
    return repository.findById(id)
      .map(User::getUserEntity)
      .orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));
  }

  @Transactional
  public UserResponse updateUserById(UUID id, UserRequest newUser) {
    User updatedUser = repository.findById(id)
      .map(user -> {
        user.setUsername(newUser.getUsername());
        return repository.save(user);
      }).orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));

    return UserResponse.builder()
      .id(updatedUser.getId())
      .username(updatedUser.getUsername())
      .build();
  }

  @Transactional
  public void deleteUserById(UUID id) {
    repository.deleteById(id);
  }

  public boolean authenticate(String username, String password) {
    User user = repository.findByUsername(username);
    return passwordEncoder.matches(password, user.getPassword());
  }
}

package kr.ac.deu.cse.scheduler.user.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.domain.UserRequest;
import kr.ac.deu.cse.scheduler.user.domain.UserResponse;
import kr.ac.deu.cse.scheduler.user.infrastructure.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserJpaRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserJpaRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserResponse createUser(final UserRequest userRequest) {
    String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
    User user = User.builder().username(userRequest.getUsername()).password(hashedPassword).build();
    user = userRepository.save(user);
    return new UserResponse(user.getId(), user.getUsername());
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserResponse getUserById(UUID id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new RuntimeException(String.format("User id %s is none", id));
    }

    return new UserResponse(user.get().getId(), user.get().getUsername());
  }

  // @Transactional
  // public User updateUserById(UUID id, User newUser) {
  //   return userRepository.findById(id).map(u -> {
  //     u.setName(newUser.getName());
  //     return userRepository.save(u);
  //   }).orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));
  // }

  @Transactional
  public void deleteUserById(UUID id) {
    userRepository.deleteById(id);
  }

  public boolean authenticate(String username, String password) {
    User user = userRepository.findByUsername(username);
    return passwordEncoder.matches(password, user.getPassword());
  }
}

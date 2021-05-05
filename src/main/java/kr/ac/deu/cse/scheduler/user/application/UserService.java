package kr.ac.deu.cse.scheduler.user.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.infrastructure.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserJpaRepository userRepository;

  @Autowired
  public UserService(UserJpaRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User createUser(final User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(UUID id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new RuntimeException(String.format("User id %s is none", id));
    }

    return user.get();
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
}

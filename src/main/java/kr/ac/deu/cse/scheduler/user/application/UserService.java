package kr.ac.deu.cse.scheduler.user.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import kr.ac.deu.cse.scheduler.user.domain.CommonUserFactory;
import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.domain.UserDataMapper;
import kr.ac.deu.cse.scheduler.user.domain.UserDtoRequestAdapter;
import kr.ac.deu.cse.scheduler.user.domain.UserDtoResponseAdapter;
import kr.ac.deu.cse.scheduler.user.domain.UserFactory;
import kr.ac.deu.cse.scheduler.user.domain.UserRequest;
import kr.ac.deu.cse.scheduler.user.domain.UserResponse;
import kr.ac.deu.cse.scheduler.user.infrastructure.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserFactory factory = new CommonUserFactory();
  private final UserDtoRequestAdapter requestAdapter = new UserDtoRequestAdapter();
  private final UserDtoResponseAdapter responseAdapter = new UserDtoResponseAdapter();

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

    User user = factory.create(newUser.getUsername(), hashedPassword, 
    						   newUser.getFirstName(), newUser.getLastName(),
    						   newUser.getPhoneNumber(), newUser.getEMail() );

    UserDataMapper dataMapper = requestAdapter.getEntity(user);
    dataMapper = repository.save(dataMapper);

    return responseAdapter.getEntity(dataMapper);
  }

  public List<UserResponse> getUsers() {
    return repository.findAll().stream()
      .map(responseAdapter::getEntity)
      .collect(Collectors.toList());
  }
  
  public UserResponse getUserById(UUID id) {
    return repository.findById(id)
      .map(responseAdapter::getEntity)
      .orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));
  }
  
  public UUID getUserByUsername(String username) {
	    UserDataMapper user = repository.findByUsername(username);
	    return user.getId();
  }
  
  @Transactional
  public UserResponse updateUserById(UUID id, UserRequest newUser) {
    UserDataMapper updatedUser = repository.findById(id)
      .map(user -> {
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return repository.save(user);
      }).orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));

    return responseAdapter.getEntity(updatedUser);
  }

  @Transactional
  public void deleteUserById(UUID id) {
    repository.deleteById(id);
  }

  public boolean authenticate(String username, String password) {
    UserDataMapper user = repository.findByUsername(username);
    return passwordEncoder.matches(password, user.getPassword());
  }
}

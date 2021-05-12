package kr.ac.deu.cse.scheduler.auth.application;

import kr.ac.deu.cse.scheduler.user.domain.User;
import kr.ac.deu.cse.scheduler.user.infrastructure.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

  private final UserJpaRepository repository;

  @Autowired
  public AuthUserDetailService(UserJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final User user = repository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    return org.springframework.security.core.userdetails.User
      .withUsername(username)
      .password(user.getPassword())
      .authorities(user.getAuthorities())
      .accountExpired(false)
      .accountLocked(false)
      .credentialsExpired(false)
      .disabled(false)
      .build();
  }
}

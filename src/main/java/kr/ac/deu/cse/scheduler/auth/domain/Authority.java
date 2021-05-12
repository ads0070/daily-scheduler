package kr.ac.deu.cse.scheduler.auth.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

  ROLE_USER;

  public String getAuthority() {
    return name();
  }
}

package kr.ac.deu.cse.scheduler.auth.domain;

import lombok.Value;

@Value
public class AuthRequest {

  String username;
  String password;
}

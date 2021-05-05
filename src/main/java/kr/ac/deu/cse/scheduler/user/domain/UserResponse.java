package kr.ac.deu.cse.scheduler.user.domain;

import java.util.UUID;
import lombok.Value;

@Value
public class UserResponse {
  UUID id;
  String username;
}

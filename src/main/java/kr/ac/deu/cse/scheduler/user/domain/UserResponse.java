package kr.ac.deu.cse.scheduler.user.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserResponse {

  UUID id;
  String username;
}

package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TodoResponse {

  UUID id;
  String title;
  String memo;
  int importance;
  boolean topFixed;
  boolean done;

}

package kr.ac.deu.cse.scheduler.schedule.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TodoRequest {

  String title;
  String memo;
  int importance;
  boolean topFixed;
  boolean done;

}

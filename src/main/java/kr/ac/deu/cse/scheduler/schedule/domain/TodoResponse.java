package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;
import lombok.Value;

@Value
public class TodoResponse {

  UUID id;
  String title;
  String memo;
  String groupName;
  String colorLabel;
  boolean topFixed;
  boolean done;

}

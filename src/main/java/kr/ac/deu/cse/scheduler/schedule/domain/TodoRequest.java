package kr.ac.deu.cse.scheduler.schedule.domain;

import lombok.Value;

@Value
public class TodoRequest {

  String title;
  String memo;
  String groupName;
  String colorLabel;
  boolean topFixed;
  boolean done;

}

package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import java.util.UUID;
import lombok.Value;

@Value
public class TaskResponse {

  UUID id;
  String title;
  String memo;
  Date beginDate;
  Date endDate;

}

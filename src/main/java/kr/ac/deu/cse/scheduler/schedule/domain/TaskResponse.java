package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TaskResponse {

  UUID id;
  String title;
  String memo;
  int importance;
  Date beginDate;
  Date endDate;

}

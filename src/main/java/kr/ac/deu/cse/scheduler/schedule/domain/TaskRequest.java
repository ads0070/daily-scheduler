package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TaskRequest {

  String title;
  String memo;
  Date beginDate;
  Date endDate;

}

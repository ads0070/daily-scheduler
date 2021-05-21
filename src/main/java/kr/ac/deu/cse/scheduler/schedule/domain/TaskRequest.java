package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import lombok.Value;

@Value
public class TaskRequest {

  String title;
  String memo;
  Date beginDate;
  Date endDate;

}

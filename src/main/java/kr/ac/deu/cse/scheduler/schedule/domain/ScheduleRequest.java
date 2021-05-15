package kr.ac.deu.cse.scheduler.schedule.domain;

import kr.ac.deu.cse.scheduler.schedule.domain.ScheduleRequest;
import lombok.Value;

@Value
public class ScheduleRequest {
	private String title;
	private String groupName;
	private String startDate;
	private String endDate;
	private String colorLabel;
	private int importance;
	private boolean done;
}

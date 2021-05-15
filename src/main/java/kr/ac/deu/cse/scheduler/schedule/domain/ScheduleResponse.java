package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;
import lombok.Value;

@Value
public class ScheduleResponse {
	private UUID id;
	private String title;
	private String groupName;
	private String startDate;
	private String endDate;
	private String colorLabel;
	private int importance;
	private boolean done;
}

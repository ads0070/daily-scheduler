package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ScheduleResponse {
	UUID id;
	String title;
	String groupName;
	String endDate;
	String colorLabel;
	boolean topFixed;
	String memo;
	int importance;
	boolean done;
}

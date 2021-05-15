package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "schedule")
@Entity
public class Schedule {
	
	@Id
	@GeneratedValue
	private UUID id;
	private String title;
	private String groupName;
	private String startDate;
	private String endDate;
	private String colorLabel;
	private int importance;
	private boolean done;
	
	@Builder
	public Schedule(String title, String groupName, String startDate,
			String endDate, String colorLabel, int importance, boolean done) {
		this.title = title;
		this.groupName = groupName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.colorLabel = colorLabel;
		this.importance = importance;
		this.done = done;
	}
}
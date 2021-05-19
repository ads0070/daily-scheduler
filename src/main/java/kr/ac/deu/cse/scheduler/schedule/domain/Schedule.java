package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private String endDate;
	private String colorLabel;
	private boolean topFixed;
	private String memo;
	private int importance;
	private boolean done;
	
	public Schedule(Builder builder) {
		this.title = builder.title;
		this.groupName = builder.groupName;
		this.endDate = builder.endDate;
		this.colorLabel = builder.colorLabel;
		this.topFixed = builder.topFixed;
		this.memo = builder.memo;
		this.importance = builder.importance;
		this.done = builder.done;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String title;
		private String groupName;
		private String endDate;
		private String colorLabel;
		private boolean topFixed;
		private String memo;
		private int importance;
		private boolean done;
		
		Builder() {
		}
	
		public Builder title(String value){
			title = value;
			return this;
		}
		public Builder groupName(String value){
			groupName = value;
			return this;
		}
		public Builder endDate(String value){
			endDate = value;
			return this;
		}
		public Builder colorLabel(String value){
			colorLabel = value;
			return this;
		}
		public Builder topFixed(boolean value){
			topFixed = value;
			return this;
		}
		public Builder memo(String value){
			memo = value;
			return this;
		}
		public Builder importance(int value){
			importance = value;
			return this;
		}
		public Builder done(boolean value){
			done = value;
			return this;
		}
		public Schedule build() {
			return new Schedule(this);
		}
	}
	
 }
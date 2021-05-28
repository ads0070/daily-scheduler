package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import javax.persistence.Entity;
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
@Table(name = "tasks")
@Entity
public class Task extends Schedule implements ScheduleStrategy {

  private Date beginDate;
  private Date endDate;

  @Builder
  public Task(String title, String memo, int importance, Date beginDate, Date endDate) {
    this.title = title;
    this.memo = memo;
    this.importance = importance;
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  @Override
  public boolean isFinished() {
    return new Date().compareTo(endDate) > 0;
  }
}

package kr.ac.deu.cse.scheduler.schedule.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
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
public class Task extends Schedule {

  private Date beginDate;
  private Date endDate;

  public Task(Date beginDate, Date endDate) {
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  @Override
  public boolean isFinished() {
    return new Date().compareTo(endDate) > 0;
  }
}

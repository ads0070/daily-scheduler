package kr.ac.deu.cse.scheduler.schedule.domain;

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
@Table(name = "todos")
@Entity
public class Todo extends Schedule {

  private String groupName;
  private String colorLabel;
  private boolean topFixed;
  private boolean done;

  public Todo(String groupName, String colorLabel, boolean topFixed, boolean done) {
    this.groupName = groupName;
    this.colorLabel = colorLabel;
    this.topFixed = topFixed;
    this.done = done;
  }

  @Override
  public boolean isFinished() {
    return this.done;
  }
}

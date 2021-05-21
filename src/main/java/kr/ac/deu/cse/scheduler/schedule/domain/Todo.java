package kr.ac.deu.cse.scheduler.schedule.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
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

  @Builder
  public Todo(String title, String memo, String groupName, String colorLabel, boolean topFixed,
    boolean done) {
    this.title = title;
    this.memo = memo;
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

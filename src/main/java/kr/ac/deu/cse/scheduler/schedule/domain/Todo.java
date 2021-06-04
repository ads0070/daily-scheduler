package kr.ac.deu.cse.scheduler.schedule.domain;

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
@Table(name = "todos")
@Entity
public class Todo extends Schedule {

  private boolean topFixed;
  private boolean done;

  @Builder
  public Todo(String title, String memo, int importance, boolean topFixed,
    boolean done) {
    this.title = title;
    this.memo = memo;
    this.importance = importance;
    this.topFixed = topFixed;
    this.done = done;
  }

  @Override
  public boolean isFinished() {
    return this.done;
  }
}

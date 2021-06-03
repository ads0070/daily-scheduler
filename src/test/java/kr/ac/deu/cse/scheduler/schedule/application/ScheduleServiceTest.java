package kr.ac.deu.cse.scheduler.schedule.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.Date;
import kr.ac.deu.cse.scheduler.schedule.domain.Schedule;
import kr.ac.deu.cse.scheduler.schedule.domain.Task;
import kr.ac.deu.cse.scheduler.schedule.domain.Todo;
import org.junit.jupiter.api.Test;

class ScheduleServiceTest {

  @Test
  void testTaskIsFinishedSuccess() {
    Schedule endedTask = Task.builder()
      .title("task")
      .memo("memo")
      .beginDate(new Date(OffsetDateTime.now().minusMonths(1).toInstant().toEpochMilli()))
      .endDate(new Date(OffsetDateTime.now().minusDays(1).toInstant().toEpochMilli()))
      .build();

    assertTrue(endedTask.isFinished());

    Schedule willEndTask = Task.builder()
      .title("task")
      .memo("memo")
      .beginDate(new Date(OffsetDateTime.now().minusMonths(1).toInstant().toEpochMilli()))
      .endDate(new Date(OffsetDateTime.now().plusDays(1).toInstant().toEpochMilli()))
      .build();

    assertFalse(willEndTask.isFinished());
  }

  @Test
  void testTodoIsFinishedSuccess() {
    Schedule doneTask = Todo.builder()
      .title("task")
      .memo("memo")
      .done(true)
      .build();

    assertTrue(doneTask.isFinished());

    Schedule willDoneTask = Todo.builder()
      .title("task")
      .memo("memo")
      .done(false)
      .build();

    assertFalse(willDoneTask.isFinished());
  }
}

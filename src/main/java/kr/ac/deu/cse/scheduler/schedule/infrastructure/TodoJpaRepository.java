package kr.ac.deu.cse.scheduler.schedule.infrastructure;

import kr.ac.deu.cse.scheduler.schedule.domain.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends ScheduleJpaRepository<Todo> {

}

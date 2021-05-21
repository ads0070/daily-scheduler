package kr.ac.deu.cse.scheduler.schedule.infrastructure;

import kr.ac.deu.cse.scheduler.schedule.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends ScheduleJpaRepository<Task> {

}

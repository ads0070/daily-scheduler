package kr.ac.deu.cse.scheduler.schedule.infrastructure;

import java.util.UUID;
import kr.ac.deu.cse.scheduler.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ScheduleJpaRepository<T extends Schedule> extends JpaRepository<T, UUID> {

}

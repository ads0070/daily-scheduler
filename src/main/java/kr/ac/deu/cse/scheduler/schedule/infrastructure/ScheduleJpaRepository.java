package kr.ac.deu.cse.scheduler.schedule.infrastructure;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<Schedule, UUID> {

	List<Schedule> findByGroupName(String groupName);

}
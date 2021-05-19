package kr.ac.deu.cse.scheduler.schedule.application;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import kr.ac.deu.cse.scheduler.schedule.domain.Schedule;
import kr.ac.deu.cse.scheduler.schedule.domain.ScheduleRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.ScheduleResponse;
import kr.ac.deu.cse.scheduler.schedule.infrastructure.ScheduleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	
	private final ScheduleJpaRepository scheduleRepository;
	
	@Autowired
	public ScheduleService(ScheduleJpaRepository scheduleRepository) {
	    this.scheduleRepository = scheduleRepository;
	}
	
	@Transactional
	public ScheduleResponse createSchedule(final ScheduleRequest scheduleRequest) {
		
		Schedule schedule = Schedule.builder().title(scheduleRequest.getTitle())
											  .groupName(scheduleRequest.getGroupName())
											  .endDate(scheduleRequest.getEndDate())
											  .colorLabel(scheduleRequest.getColorLabel())
											  .topFixed(scheduleRequest.isTopFixed())
											  .memo(scheduleRequest.getMemo())
											  .importance(scheduleRequest.getImportance())
											  .done(scheduleRequest.isDone())
											  .build();
		
		schedule = scheduleRepository.save(schedule);
		
	    return ScheduleResponse.builder()
	    		.id(schedule.getId())
	    		.title(schedule.getTitle())
				.groupName(schedule.getGroupName())
				.endDate(schedule.getEndDate())
				.colorLabel(schedule.getColorLabel())
				.topFixed(schedule.isTopFixed())
				.memo(schedule.getMemo())
				.importance(schedule.getImportance())
				.done(schedule.isDone())
				.build();
	}
	
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}
	
	public List<Schedule> getGroupSchedules(String groupName) {
		return scheduleRepository.findByGroupName(groupName);
	}
	
	public ScheduleResponse getScheduleById(UUID id) {
	    Schedule schedule = scheduleRepository.findById(id)
	      .orElseThrow(() -> new RuntimeException(String.format("User id %s is none", id)));

	    return ScheduleResponse.builder()
	    		.id(schedule.getId())
	    		.title(schedule.getTitle())
				.groupName(schedule.getGroupName())
				.endDate(schedule.getEndDate())
				.colorLabel(schedule.getColorLabel())
				.topFixed(schedule.isTopFixed())
				.memo(schedule.getMemo())
				.importance(schedule.getImportance())
				.done(schedule.isDone()).build();
	  }
	
	@Transactional
	public void deleteScheduleById(UUID id) {
		scheduleRepository.deleteById(id);
	}
}
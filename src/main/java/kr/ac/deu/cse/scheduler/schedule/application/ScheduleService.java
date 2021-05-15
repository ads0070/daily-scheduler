package kr.ac.deu.cse.scheduler.schedule.application;

import java.util.List;
import java.util.Optional;
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
		Schedule schedule = Schedule.builder().title(scheduleRequest.getTitle()).
				groupName(scheduleRequest.getGroupName()).startDate(scheduleRequest.getStartDate()).
				endDate(scheduleRequest.getEndDate()).colorLabel(scheduleRequest.getColorLabel()).
				importance(scheduleRequest.getImportance()).done(scheduleRequest.isDone()).build();
		schedule = scheduleRepository.save(schedule);
	    return new ScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getGroupName(),schedule.getStartDate(),
	    		schedule.getEndDate(),schedule.getColorLabel(),schedule.getImportance(),schedule.isDone());
	}
	
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}
	
	public ScheduleResponse getScheduleById(UUID id) {
		Optional<Schedule> schedule = scheduleRepository.findById(id);
		
		if (schedule.isEmpty()) {
			throw new RuntimeException(String.format("Schedule id %s is none", id));
		}
		
		return new ScheduleResponse(schedule.get().getId(),schedule.get().getTitle(),schedule.get().getGroupName(),schedule.get().getStartDate(),
	    		schedule.get().getEndDate(),schedule.get().getColorLabel(),schedule.get().getImportance(),schedule.get().isDone());
	}
	
	@Transactional
	public void deleteScheduleById(UUID id) {
		scheduleRepository.deleteById(id);
	}
}
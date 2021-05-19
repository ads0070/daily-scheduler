package kr.ac.deu.cse.scheduler.schedule.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.schedule.application.ScheduleService;
import kr.ac.deu.cse.scheduler.schedule.domain.Schedule;
import kr.ac.deu.cse.scheduler.schedule.domain.ScheduleRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/schedules")
@RepositoryRestController
public class ScheduleController {
	
	private final ScheduleService scheduleService;
	
	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
		
	}
	@ResponseBody
	@PostMapping
	public ScheduleResponse createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
		return scheduleService.createSchedule(scheduleRequest);
	}
	
	@ResponseBody
	@GetMapping
	public List<Schedule> getAllSchedules() {
		return scheduleService.getAllSchedules();
	}
	
	@ResponseBody
	@GetMapping("/{id}")
	public ScheduleResponse getOneSchedule(@PathVariable UUID id) {
		return scheduleService.getScheduleById(id);
	}
	
	@ResponseBody
	@GetMapping("/group/{groupName}")
	public List<Schedule> getGroupSchedules(@PathVariable String groupName) {
		return scheduleService.getGroupSchedules(groupName);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteSchedule(@PathVariable UUID id) {
		scheduleService.deleteScheduleById(id);
	}
	
	
}
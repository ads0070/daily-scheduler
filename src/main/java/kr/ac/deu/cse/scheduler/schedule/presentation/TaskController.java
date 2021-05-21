package kr.ac.deu.cse.scheduler.schedule.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.schedule.application.TaskService;
import kr.ac.deu.cse.scheduler.schedule.domain.TaskRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/tasks")
@RepositoryRestController
public class TaskController {

  private final TaskService service;

  @Autowired
  public TaskController(TaskService service) {
    this.service = service;
  }

  @ResponseBody
  @PostMapping
  public ResponseEntity<?> createResource(@RequestBody TaskRequest request) {
    return ResponseEntity.ok(service.createTask(request));
  }

  @ResponseBody
  @GetMapping
  public ResponseEntity<?> retrieveResources() {
    List<TaskResponse> tasks = service.retrieveTasks();

    return ResponseEntity.ok(tasks);
  }

  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<?> retrieveResource(@PathVariable UUID id) {
    TaskResponse response = service.retrieveTaskById(id);

    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @PatchMapping("/{id}")
  public ResponseEntity<?> updateResource(@PathVariable UUID id, @RequestBody TaskRequest request) {
    TaskResponse response = service.updateTaskById(id, request);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteResource(@PathVariable UUID id) {
    service.deleteTaskById(id);

    return ResponseEntity.noContent().build();
  }
}

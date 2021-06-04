package kr.ac.deu.cse.scheduler.schedule.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.interfaces.AbstractController;
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
public class TaskController extends AbstractController<TaskRequest, UUID> {

  private final TaskService service;

  @Autowired
  public TaskController(TaskService service) {
    this.service = service;
  }

  @Override
  @ResponseBody
  @PostMapping
  public ResponseEntity<?> create(@RequestBody TaskRequest request) {
    return ResponseEntity.ok(service.createTask(request));
  }

  @Override
  @ResponseBody
  @GetMapping
  public ResponseEntity<?> readAll() {
    List<TaskResponse> tasks = service.retrieveTasks();

    return ResponseEntity.ok(tasks);
  }

  @Override
  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<?> readOne(@PathVariable UUID id) {
    TaskResponse response = service.retrieveTaskById(id);

    return ResponseEntity.ok(response);
  }

  @Override
  @ResponseBody
  @PostMapping("/patch/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TaskRequest request) {
    TaskResponse response = service.updateTaskById(id, request);

    return ResponseEntity.ok(response);
  }

  @Override
  @GetMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    service.deleteTaskById(id);

    return ResponseEntity.noContent().build();
  }
}

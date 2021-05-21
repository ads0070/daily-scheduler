package kr.ac.deu.cse.scheduler.schedule.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.schedule.application.TodoService;
import kr.ac.deu.cse.scheduler.schedule.domain.TodoRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.TodoResponse;
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

@RequestMapping("/todos")
@RepositoryRestController
public class TodoController {

  private final TodoService service;

  @Autowired
  public TodoController(TodoService service) {
    this.service = service;
  }

  @ResponseBody
  @PostMapping
  public ResponseEntity<?> createResource(@RequestBody TodoRequest todo) {
    return ResponseEntity.ok(service.createTodo(todo));
  }

  @ResponseBody
  @GetMapping
  public ResponseEntity<?> retrieveResources() {
    List<TodoResponse> response = service.retrieveTodos();

    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<?> retrieveResource(@PathVariable UUID id) {
    TodoResponse response = service.retrieveTodoById(id);

    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @PatchMapping("/{id}")
  public ResponseEntity<?> updateResource(@PathVariable UUID id, @RequestBody TodoRequest request) {
    TodoResponse response = service.updateTodoById(id, request);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteResource(@PathVariable UUID id) {
    service.deleteTodoById(id);

    return ResponseEntity.noContent().build();
  }
}

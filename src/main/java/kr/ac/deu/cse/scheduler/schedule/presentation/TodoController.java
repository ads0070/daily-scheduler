package kr.ac.deu.cse.scheduler.schedule.presentation;

import java.util.List;
import java.util.UUID;
import kr.ac.deu.cse.scheduler.interfaces.AbstractController;
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
public class TodoController extends AbstractController<TodoRequest, UUID> {

  private final TodoService service;

  @Autowired
  public TodoController(TodoService service) {
    this.service = service;
  }

  @Override
  @ResponseBody
  @PostMapping
  public ResponseEntity<?> create(@RequestBody TodoRequest request) {
    return ResponseEntity.ok(service.createTodo(request));
  }

  @Override
  @ResponseBody
  @GetMapping
  public ResponseEntity<?> readAll() {
    List<TodoResponse> tasks = service.retrieveTodos();

    return ResponseEntity.ok(tasks);
  }

  @Override
  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<?> readOne(@PathVariable UUID id) {
    TodoResponse response = service.retrieveTodoById(id);

    return ResponseEntity.ok(response);
  }

  @Override
  @ResponseBody
  @PatchMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TodoRequest request) {
    TodoResponse response = service.updateTodoById(id, request);

    return ResponseEntity.ok(response);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    service.deleteTodoById(id);

    return ResponseEntity.noContent().build();
  }
}

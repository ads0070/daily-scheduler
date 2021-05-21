package kr.ac.deu.cse.scheduler.schedule.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kr.ac.deu.cse.scheduler.schedule.domain.Todo;
import kr.ac.deu.cse.scheduler.schedule.domain.TodoRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.TodoResponse;
import kr.ac.deu.cse.scheduler.schedule.infrastructure.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoJpaRepository repository;

  @Autowired
  public TodoService(TodoJpaRepository repository) {
    this.repository = repository;
  }

  public TodoResponse createTodo(final TodoRequest newTodo) {
    Todo todo = Todo.builder()
      .title(newTodo.getTitle())
      .memo(newTodo.getMemo())
      .groupName(newTodo.getGroupName())
      .colorLabel(newTodo.getColorLabel())
      .topFixed(newTodo.isTopFixed())
      .done(newTodo.isDone())
      .build();

    todo = repository.save(todo);

    return TodoResponse.builder()
      .id(todo.getId())
      .title(todo.getTitle())
      .memo(todo.getMemo())
      .groupName(todo.getGroupName())
      .colorLabel(todo.getColorLabel())
      .topFixed(todo.isTopFixed())
      .done(todo.isDone())
      .build();
  }

  public List<TodoResponse> retrieveTodos() {
    return repository.findAll().stream().map(
      todo -> TodoResponse.builder()
        .id(todo.getId())
        .title(todo.getTitle())
        .memo(todo.getMemo())
        .groupName(todo.getGroupName())
        .colorLabel(todo.getColorLabel())
        .topFixed(todo.isTopFixed())
        .done(todo.isDone())
        .build()
    ).collect(Collectors.toList());
  }

  public TodoResponse retrieveTodoById(UUID id) {
    return repository.findById(id)
      .map(todo -> TodoResponse.builder()
        .id(todo.getId())
        .title(todo.getTitle())
        .memo(todo.getMemo())
        .groupName(todo.getGroupName())
        .colorLabel(todo.getColorLabel())
        .topFixed(todo.isTopFixed())
        .done(todo.isDone())
        .build()
      ).orElseThrow();
  }

  public TodoResponse updateTodoById(UUID id, final TodoRequest newTodo) {
    Todo updatedTodo = repository.findById(id)
      .map(todo -> {
        todo.setTitle(newTodo.getTitle());
        todo.setMemo(newTodo.getMemo());
        todo.setGroupName(newTodo.getGroupName());
        todo.setColorLabel(newTodo.getColorLabel());
        todo.setTopFixed(newTodo.isTopFixed());
        todo.setDone(newTodo.isDone());
        return repository.save(todo);
      }).orElseThrow();

    return TodoResponse.builder()
      .id(updatedTodo.getId())
      .title(updatedTodo.getTitle())
      .memo(updatedTodo.getMemo())
      .groupName(updatedTodo.getGroupName())
      .colorLabel(updatedTodo.getColorLabel())
      .topFixed(updatedTodo.isTopFixed())
      .done(updatedTodo.isDone())
      .build();
  }

  public void deleteTodoById(UUID id) {
    repository.deleteById(id);
  }
}

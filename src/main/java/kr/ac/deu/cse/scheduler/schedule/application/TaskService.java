package kr.ac.deu.cse.scheduler.schedule.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kr.ac.deu.cse.scheduler.schedule.domain.Task;
import kr.ac.deu.cse.scheduler.schedule.domain.TaskRequest;
import kr.ac.deu.cse.scheduler.schedule.domain.TaskResponse;
import kr.ac.deu.cse.scheduler.schedule.infrastructure.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskJpaRepository repository;

  @Autowired
  public TaskService(TaskJpaRepository repository) {
    this.repository = repository;
  }

  public TaskResponse createTask(final TaskRequest newTask) {
    Task task = Task.builder()
      .title(newTask.getTitle())
      .memo(newTask.getMemo())
      .importance(newTask.getImportance())
      .beginDate(newTask.getBeginDate())
      .endDate(newTask.getEndDate())
      .build();

    task = repository.save(task);

    return TaskResponse.builder()
      .id(task.getId())
      .title(task.getTitle())
      .memo(task.getMemo())
      .importance(task.getImportance())
      .beginDate(newTask.getBeginDate())
      .endDate(newTask.getEndDate())
      .build();
  }

  public List<TaskResponse> retrieveTasks() {
    return repository.findAll().stream().map(
      task -> TaskResponse.builder()
        .id(task.getId())
        .title(task.getTitle())
        .memo(task.getMemo())
        .importance(task.getImportance())
        .beginDate(task.getBeginDate())
        .endDate(task.getEndDate())
        .build()
    ).collect(Collectors.toList());
  }

  public TaskResponse retrieveTaskById(UUID id) {
    return repository.findById(id)
      .map(task -> TaskResponse.builder()
        .id(task.getId())
        .title(task.getTitle())
        .memo(task.getMemo())
        .importance(task.getImportance())
        .beginDate(task.getBeginDate())
        .endDate(task.getEndDate())
        .build()
      ).orElseThrow();
  }

  public TaskResponse updateTaskById(UUID id, final TaskRequest newTask) {
    Task updatedTask = repository.findById(id)
      .map(task -> {
        task.setTitle(newTask.getTitle());
        task.setMemo(newTask.getMemo());
        task.setImportance(newTask.getImportance());
        task.setBeginDate(newTask.getBeginDate());
        task.setEndDate(newTask.getEndDate());
        return repository.save(task);
      }).orElseThrow();

    return TaskResponse.builder()
      .id(updatedTask.getId())
      .title(updatedTask.getTitle())
      .memo(updatedTask.getMemo())
      .importance(updatedTask.getImportance())
      .beginDate(updatedTask.getBeginDate())
      .endDate(updatedTask.getEndDate())
      .build();
  }

  public void deleteTaskById(UUID id) {
    repository.deleteById(id);
  }
}

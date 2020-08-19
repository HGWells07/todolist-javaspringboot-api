package com.seed.todolistapi.Services;

import java.util.List;
import java.util.UUID;

import com.seed.todolistapi.Models.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  private final TaskAccessService taskAccessService;

  @Autowired
  public TaskService(TaskAccessService taskAccessService) {
    this.taskAccessService = taskAccessService;
  }

  public List<Task> getTaskList(Boolean undoneOnly, String query) {
    return taskAccessService.getTaskList(undoneOnly, query);
  }

  public Task getTask(UUID id) {
    return taskAccessService.getTask(id);
  }

  public int checkTask(UUID id) {
    return taskAccessService.checkTask(id);
  }

  public int createTask(Task task) {
    return taskAccessService.createTask(task);
  }

  public int editTask(UUID id, Task task) {
    return taskAccessService.editTask(id, task);
  }

  public int deleteTask(UUID id) {
    return taskAccessService.deleteTask(id);
  }
}
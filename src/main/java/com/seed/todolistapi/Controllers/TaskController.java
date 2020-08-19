package com.seed.todolistapi.Controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.seed.todolistapi.Models.Task;
import com.seed.todolistapi.Services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getTaskList(
    @RequestParam(required = false) String search, 
    @RequestParam(required = false, defaultValue = "1") Integer undoneOnly
  ) {
    if(search==null || search.equals("")) search="";
    if(undoneOnly==null || undoneOnly==1) undoneOnly=1;
    return taskService.getTaskList(undoneOnly!=0, search);
  }

  @GetMapping(path = "/{id}")
  public Task getTask(@PathVariable("id") UUID id) {
    return taskService.getTask(id);
  }

  @PostMapping
  public int createTask(@RequestBody @Valid Task task
  ){
    return taskService.createTask(task);
  }

  @PutMapping(path = "/check/{id}")
  public int checkTask(
    @PathVariable("id") UUID id
  ){
    return taskService.checkTask(id);
  }

  @PutMapping(path = "{id}")
  public int editTask(
    @PathVariable("id") UUID id,
    @RequestBody Task task
  ){
    return taskService.editTask(id, task);
  }

  @DeleteMapping(path = "{id}")
  public int deleteTask(
    @PathVariable("id") UUID id
  ){
    return taskService.deleteTask(id);
  }

}
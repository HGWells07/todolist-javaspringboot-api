package com.seed.todolistapi.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.seed.todolistapi.Models.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TaskAccessService {
  @Autowired
  private JdbcTemplate jdbc;

  public List<Task> getTaskList(boolean undoneOnly, String  query){
    String temp = "All";
    if(undoneOnly) temp="Undone";
    String sql;
    if(query.equals("") || query.isBlank() || query==null){
      sql = "CALL Get"+temp+"TasksNames();";
    } else {
      sql = "CALL Search"+temp+"Tasks('"+query+"');";
    }
    return jdbc.query(sql, mapTaskList());
  }

  public Task getTask(UUID id){
    if(idExists(id)){
      String sql = "CALL GetTask('"+id.toString()+"');";
      return jdbc.query(sql, mapTask()).get(0);
    } return new Task("Error: La tarea no existe");
  }

  public int checkTask(UUID id){
    if(idExists(id)){
      String sql = "CALL ChangeDoneTask('"+id.toString()+"');";
      return jdbc.update(sql);
    } return 0;
  }

  public int createTask(Task task){
    String date="NULL";
    String nameAux="";
    String detailsAux="";

    if(task.getDue_date()!=null){
      Date temp_date = Date.valueOf(task.getDue_date());
      date = "'"+temp_date.toString()+"'";
    }

    if(task.getDetails()!=null){
      detailsAux=task.getDetails();
    }

    if(task.getName()!=null){
      nameAux=task.getName();
    }

    String sql = "CALL CreateTask('"+nameAux+"', '"+detailsAux+"', "+date+");";
    return jdbc.update(sql);
  }

  public int editTask(UUID id, Task task){
    if(idExists(id)){
      Date temp_date = Date.valueOf(task.getDue_date());
      String sql = "CALL EditTask('"+id.toString()+"', '"+task.getName()+"', '"+task.getDetails()+"', '"+temp_date.toString()+"');";
      return jdbc.update(sql);
    } return 0;
  }

  public int deleteTask(UUID id){
    if(idExists(id)){
      String sql = "CALL DeleteTask('"+id.toString()+"');";
      return jdbc.update(sql);
    } return 0;
  }

  private boolean idExists(UUID id){
    try{
      jdbc.execute("SELECT TOP 1 Tasks.id FROM Tasks WHERE Tasks.id = '"+id.toString()+"';");
    } catch(DataAccessException e){
      return false;
    }
    return true;
  }

  private RowMapper<Task> mapTaskList() {
    return (resultSet, i) -> {
      String studentIdStr = resultSet.getString("Id");
      UUID id = UUID.fromString(studentIdStr);

      String task_name = resultSet.getString("TaskName");
      boolean done = resultSet.getInt("Done")==1;

      return new Task(
              id,
              task_name,
              done
      );
    };
  }

  private RowMapper<Task> mapTask() {
    return (resultSet, i) -> {
      String studentIdStr = resultSet.getString("Id");
      UUID id = UUID.fromString(studentIdStr);

      String task_name = resultSet.getString("TaskName");
      String details = resultSet.getString("Details");
      LocalDate due_date = resultSet.getDate("DueDate").toLocalDate();
      boolean done = resultSet.getInt("Done")==1;

      return new Task(
              id,
              task_name,
              details,
              due_date,
              done
      );
    };
  }

}
package com.seed.todolistapi.Models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="Id")
  private UUID id;

  @Column(name="TaskName")
  private String name;

  @Column(name="Details")
  private String details;

  @Column(name="DueDate")
  private LocalDate due_date;

  @Column(name="Done")
  private boolean done;
  

  public Task() {
    this.name = null;
    this.details = null;
    this.due_date = null;
    this.done = false;
  }

  public Task(UUID id, String name, String details, LocalDate due_date, boolean done) {
    this.id = id;
    this.name = name;
    this.details = details;
    this.due_date = due_date;
    this.done = done;
  }

  public Task(UUID id, String name, String details, LocalDate due_date) {
    this.id = id;
    this.name = name;
    this.details = details;
    this.due_date = due_date;
    this.done = false;
  }

  public Task(String name, String details, LocalDate due_date) {
    this.name = name;
    this.details = details;
    this.due_date = due_date;
    this.done = false;
  }

  public Task(UUID id, String name, String details) {
    this.id = id;
    this.name = name;
    this.details = details;
    this.due_date = null;
    this.done = false;
  }

  public Task(String name, String details) {
    this.name = name;
    this.details = details;
    this.due_date = null;
    this.done = false;
  }

  public Task(UUID id, String name, boolean done) {
    this.id = id;
    this.name = name;
    this.done = done;
  }

  public Task(String name) {
    this.name = name;
    this.details = null;
    this.due_date = null;
    this.done = false;
  }

  public Task(UUID id, String name, LocalDate due_date) {
    this.id = id;
    this.name = name;
    this.details = "";
    this.due_date = due_date;
    this.done = false;
  }

  public Task(UUID id, String name) {
    this.id = id;
    this.name = name;
    this.details = "";
    this.due_date = null;
    this.done = false;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetails() {
    return this.details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public LocalDate getDue_date() {
    return this.due_date;
  }

  public void setDue_date(LocalDate due_date) {
    this.due_date = due_date;
  }

  public boolean isDone() {
    return this.done;
  }

  public boolean getDone() {
    return this.done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", details='" + getDetails() + "'" +
      ", due_date='" + getDue_date() + "'" +
      ", done='" + isDone() + "'" +
      "}";
  }


}
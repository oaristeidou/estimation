package com.software.forecasting.model;

import java.util.Set;

/**
 * Created by odyssefs on 18.03.17.
 */
public class HistoryData {
  private Integer effort;
  private Set<String> tasks;

  public Set<String> getTasks() {
    return tasks;
  }

  public void setTasks(Set<String> tasks) {
    this.tasks = tasks;
  }



  public Integer getEffort() {
    return effort;
  }

  public void setEffort(Integer effort) {
    this.effort = effort;
  }
}

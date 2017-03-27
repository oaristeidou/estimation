package com.software.forecasting.model;

import java.util.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FutureTaskBean {
  private Integer effort;
  private Set<String> tasks;
  public Set<Integer> categoryEfforts;

  public FutureTaskBean() {
  }

  public FutureTaskBean(Integer effort, Set<String> tasks) {
    this.effort = effort;
    this.tasks = tasks;
  }

  public FutureTaskBean(Set<String> tasks) {
    this.tasks = tasks;
  }

  public FutureTaskBean(Integer effort, Set<String> tasks, Set<Integer> categoryEfforts) {
    this.effort = effort;
    this.tasks = tasks;
    this.categoryEfforts = categoryEfforts;
  }

  public static void categorise(List<FutureTaskBean> futureTaskList, List<HistoryDataBean> historicalData) {
    futureTaskList.forEach((futureTask) -> {
      Set<Integer> futureTaskEfforts = new HashSet<>();
      historicalData.forEach(
          (historicalEntry) -> futureTask.getTasks().forEach(
              (task) -> {
                if (historicalEntry.getTasks().contains(task))
                  futureTaskEfforts.add(historicalEntry.getEffort());
              }
          )
      );
      futureTask.setCategoryEfforts(futureTaskEfforts);
    });
  }

  public Set<Integer> getCategoryEfforts() {
    return categoryEfforts;
  }

  public void setCategoryEfforts(Set<Integer> categoryEfforts) {
    this.categoryEfforts = categoryEfforts;
  }

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

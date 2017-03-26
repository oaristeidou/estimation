package com.software.forecasting.model;

import java.util.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FutureTask {
  private Integer effort;
  private Set<String> tasks;

  public static Map<List<String>, Set<Integer>> categorise(Map<Integer, List<String>> futureTasks, Map<Integer, Set<String>> historicalData) {
    Map<List<String>, Set<Integer>> categorisedFutureTasks = new HashMap<>();
    futureTasks.forEach((effort, tasks) -> collectCategoriesByHistoryData(historicalData, categorisedFutureTasks, tasks));
    return categorisedFutureTasks;
  }

  private static void collectCategoriesByHistoryData(Map<Integer, Set<String>> historicalData, Map<List<String>, Set<Integer>> categorisedFutureTasks, List<String> tasks) {
    Set<Integer> indexFoundedInHistoryData = new HashSet<>();
    historicalData.forEach(
        (effortHD, tasksHD) -> tasks.forEach(
            (task) -> {
              if (tasksHD.contains(task))
                indexFoundedInHistoryData.add(effortHD);
            }
        )
    );
    categorisedFutureTasks.put(tasks, indexFoundedInHistoryData);
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

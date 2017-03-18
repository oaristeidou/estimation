package com.software.forecasting.model;

import java.util.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FutureTask {
  protected Map<List<String>, Set<Integer>> categorise(Map<Integer, List<String>> futureTasks, Map<Integer, List<String>> historicalData) {
    Map<List<String>, Set<Integer>> categorisedFutureTasks = new HashMap<>();

    futureTasks.forEach(
        (index, tasks) -> collectCategoriesByHistoryData(historicalData, categorisedFutureTasks, tasks)
    );
    return categorisedFutureTasks;
  }

  private void collectCategoriesByHistoryData(Map<Integer, List<String>> historicalData, Map<List<String>, Set<Integer>> categorisedFutureTasks, List<String> tasks) {
    Set<Integer> indexFoundedInHistoryData = new HashSet<Integer>();
    historicalData.forEach(
        (indexHD, tasksHD) -> {
          final Integer[] countTasks = {0};
          tasks.forEach(
              (task) -> {
                if (tasksHD.contains(task))
                  countTasks[0]++;
              }
          );
          if (countTasks[0].equals(tasks.size()))
            indexFoundedInHistoryData.add(indexHD);
        }
    );
    categorisedFutureTasks.put(tasks, indexFoundedInHistoryData);
  }

  protected Map<Integer, Set<String>> readHistoricalData (){
    return null;
  }
}

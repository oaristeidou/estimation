package com.software.forecasting.service;

import com.software.forecasting.model.FutureTaskBean;
import com.software.forecasting.model.HistoryDataBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by odyssefs on 28.03.17.
 */
public class ForecastingCategorizationService {

  public List<FutureTaskBean> categorise(List<FutureTaskBean> futureTaskList, List<HistoryDataBean> historicalData) {
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
    return futureTaskList;
  }
}

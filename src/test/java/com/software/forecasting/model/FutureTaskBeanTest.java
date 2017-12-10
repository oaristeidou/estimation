package com.software.forecasting.model;


import com.software.forecasting.service.ForecastingCategorizationService;
import mockit.Tested;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FutureTaskBeanTest {

  @Tested
  private ForecastingCategorizationService forecastingCategorizationService;

  @Test
  public void testCategorise() throws Exception {

    List<FutureTaskBean> futureTasksList = new ArrayList<>();
    futureTasksList.add(new FutureTaskBean(1, new HashSet<>(Arrays.asList("x"))));
    futureTasksList.add(new FutureTaskBean(2, new HashSet<>(Arrays.asList("x", "y"))));
    futureTasksList.add(new FutureTaskBean(3, new HashSet<>(Arrays.asList("-"))));

    List<HistoryDataBean> historicalData = new ArrayList<>();
    historicalData.add(new HistoryDataBean(1, new HashSet<>(Arrays.asList("x"))));
    historicalData.add(new HistoryDataBean(2, new HashSet<>(Arrays.asList("y"))));
    historicalData.add(new HistoryDataBean(3, new HashSet<>(Arrays.asList("x", "y"))));
    historicalData.add(new HistoryDataBean(4, new HashSet<>(Arrays.asList("-"))));
    historicalData.add(new HistoryDataBean(5, new HashSet<>(Arrays.asList("z"))));
    historicalData.add(new HistoryDataBean(6, new HashSet<>(Arrays.asList("x"))));
    historicalData.add(new HistoryDataBean(7, new HashSet<>(Arrays.asList("-"))));
    historicalData.add(new HistoryDataBean(8, new HashSet<>(Arrays.asList("y"))));
    historicalData.add(new HistoryDataBean(9, new HashSet<>(Arrays.asList("y", "x"))));

    List<CategoriseBean> exceptedCategorisedData = new ArrayList<>();
    exceptedCategorisedData.add(new CategoriseBean(new FutureTaskBean(new HashSet<>(Arrays.asList("x"))), new HashSet<>(Arrays.asList(1, 3, 6, 9))));
    exceptedCategorisedData.add(new CategoriseBean(new FutureTaskBean(new HashSet<>(Arrays.asList("x", "y"))), new HashSet<>(Arrays.asList(1, 2, 3, 6, 8, 9))));
    exceptedCategorisedData.add(new CategoriseBean(new FutureTaskBean(new HashSet<>(Arrays.asList("-"))), new HashSet<>(Arrays.asList(4, 7))));

    List<FutureTaskBean> futureTaskBeanList = forecastingCategorizationService.categorise(futureTasksList, historicalData);
    final int[] index = {0};
    futureTaskBeanList.forEach(
        (futureTask) -> {
          assertEquals(futureTask.getCategoryEfforts(), exceptedCategorisedData.get(index[0]).getCategoryEfforts());
          index[0]++;
        }
    );
  }

}
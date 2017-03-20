package com.software.forecasting.model;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by odyssefs on 19.03.17.
 */
public class ForecastingSimulationTest {

  @Test
  public void testSimulate() throws Exception {
    Map<List<String>, Set<Integer>> futureTasksCategories = new HashMap<>();
    futureTasksCategories.put(new ArrayList<String>(Arrays.asList("x")), new HashSet<>(Arrays.asList(1, 3, 6, 9)));
    futureTasksCategories.put(new ArrayList<String>(Arrays.asList("x", "y")), new HashSet<>(Arrays.asList(1, 2, 3, 6, 8, 9)));
    futureTasksCategories.put(new ArrayList<String>(Arrays.asList("-")), new HashSet<>(Arrays.asList(4, 7)));
    ForecastingSimulation forecastingSimulation = new ForecastingSimulation();

    Map<Integer, List<String>> futureTasks = new HashMap<>();
    futureTasks.put(1, new ArrayList<String>(Arrays.asList("x")));
    futureTasks.put(2, new ArrayList<String>(Arrays.asList("x", "y")));
    futureTasks.put(3, new ArrayList<String>(Arrays.asList("-")));

    assertTrue(forecastingSimulation.simulate(futureTasksCategories, futureTasks).size() == 20);
  }
}
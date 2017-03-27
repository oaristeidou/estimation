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
    List<FutureTaskBean> futureTasks = new ArrayList<>();
    futureTasks.add(new FutureTaskBean(1, new HashSet<>(Arrays.asList("x")), new HashSet<>(Arrays.asList(1, 3, 6, 9))));
    futureTasks.add(new FutureTaskBean(2, new HashSet<>(Arrays.asList("x", "y")), new HashSet<>(Arrays.asList(1, 2, 3, 6, 8, 9))));
    futureTasks.add(new FutureTaskBean(3, new HashSet<>(Arrays.asList("-")), new HashSet<>(Arrays.asList(4, 7))));

    assertTrue(ForecastingSimulation.simulate(futureTasks, 20).size() == 20);
  }
}
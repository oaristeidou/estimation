package com.software.forecasting.model;


import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FutureTaskTest {

  @Test
  public void testCategorise() throws Exception {

    Map<Integer, List<String>> futureTasks = new HashMap<>();
    futureTasks.put(1, new ArrayList<String>(Arrays.asList("x")));
    futureTasks.put(2, new ArrayList<String>(Arrays.asList("x", "y")));
    futureTasks.put(3, new ArrayList<String>(Arrays.asList("-")));

    Map<Integer, List<String>> historicalData= new HashMap<>();
    historicalData.put(1, new ArrayList<String>(Arrays.asList("x")));
    historicalData.put(2, new ArrayList<String>(Arrays.asList("y")));
    historicalData.put(3, new ArrayList<String>(Arrays.asList("x", "y")));
    historicalData.put(4, new ArrayList<String>(Arrays.asList("-")));
    historicalData.put(5, new ArrayList<String>(Arrays.asList("z")));
    historicalData.put(6, new ArrayList<String>(Arrays.asList("x")));
    historicalData.put(7, new ArrayList<String>(Arrays.asList("-")));
    historicalData.put(8, new ArrayList<String>(Arrays.asList("y")));
    historicalData.put(9, new ArrayList<String>(Arrays.asList("y", "x")));

    Map<List<String>, Set<Integer>> exceptedCategorisedData = new HashMap<>();
    exceptedCategorisedData.put(new ArrayList<String>(Arrays.asList("x")), new HashSet<>(Arrays.asList(1, 3, 6, 9)));
    exceptedCategorisedData.put(new ArrayList<String>(Arrays.asList("x", "y")), new HashSet<>(Arrays.asList(3, 9)));
    exceptedCategorisedData.put(new ArrayList<String>(Arrays.asList("-")), new HashSet<>(Arrays.asList(4, 7)));
    FutureTask futureTask = new FutureTask();

    Map<List<String>, Set<Integer>> categoriseData = futureTask.categorise(futureTasks, historicalData);

    categoriseData.forEach(
        (futureTasksValues, historicalDataValues) -> {
          Set<Integer> expectedIndexValue = exceptedCategorisedData.get(futureTasksValues);
          assertEquals(historicalDataValues, expectedIndexValue);
        }
    );


  }

}
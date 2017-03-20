package com.software.forecasting.model;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by odyssefs on 19.03.17.
 */
public class ForecastingSimulation {

  public List<Integer> simulate(Map<List<String>, Set<Integer>> futureTasksCategories, Map<Integer, List<String>> futureTasks) {
    List<Integer> efforts = new ArrayList<>();

    IntStream
        .range(0, 20)
        .forEach(
            i -> {
              final Integer[] sum = {0};
              Random randomGenerator = new Random();
              futureTasks.forEach(
                  (effort, tasks) -> IntStream.range(0, effort).forEach(j -> {
                        Object[] integers = futureTasksCategories.get(tasks).toArray();
                        int randomIndex = randomGenerator.nextInt(integers.length);
                        sum[0] += (Integer) integers[randomIndex];
                      }
                  )
              );
              efforts.add(sum[0]);
            }
        );
    Collections.sort(efforts);
    return efforts;
  }
}

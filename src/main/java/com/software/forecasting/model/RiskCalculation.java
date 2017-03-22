package com.software.forecasting.model;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by odyssefs on 20.03.17.
 */
public class RiskCalculation {
  public Map<Integer, List<Integer>> calculateRisk (List<Integer> efforts){
    Map<Integer, Integer> partionedMap = createEffortPartitions (efforts);
    Map<Integer, List<Integer>> simulatedEfforts = mergeDuplicationEfforts (efforts, partionedMap);
    return simulatedEfforts;

  }

  private Map<Integer, List<Integer>> mergeDuplicationEfforts(List<Integer> efforts, Map<Integer, Integer> partionedMap) {
    Map<Integer, List<Integer>> result = new HashMap<>();
    final int[] counter = {0};
    final Integer[] prevPercent = {90};
    efforts.forEach(effort -> {
      if (partionedMap.get(effort) != null)
        prevPercent[0] = partionedMap.get(effort);
      result.put(effort, Arrays.asList(Collections.frequency(efforts, effort), prevPercent[0]));
      counter[0]++;
    });

    return result;
  }

  private Map<Integer, Integer> createEffortPartitions(List<Integer> efforts) {
    int partitionRisk = 20;
    Map<Integer, Integer> result = new HashMap<>();
    IntStream.range(0, 10).forEach(
        i -> {
          int risk = i * 10;
          double index = (100.0 - risk) / 100 * partitionRisk;
          Integer effortKey = efforts.get((int) index - 1);
          if (!result.containsKey(effortKey))
            result.put(effortKey, risk);
        });
    return result;
  }
}

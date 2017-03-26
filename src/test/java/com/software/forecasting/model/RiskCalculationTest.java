package com.software.forecasting.model;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by odyssefs on 21.03.17.
 */
public class RiskCalculationTest {

  @Test
  public void testCalculateRisk() throws Exception {
    List<Integer> efforts = Arrays.asList(15, 17, 17, 20, 24, 27, 27, 27, 30, 34, 35, 35, 38, 38, 39, 42, 42, 45, 46, 48);
    Map<Integer, List<Integer>> expectedMap = new HashMap<>();
    expectedMap.put(15, Arrays.asList(1, 90));
    expectedMap.put(17, Arrays.asList(2, 90));
    expectedMap.put(20, Arrays.asList(1, 80));
    expectedMap.put(24, Arrays.asList(1, 80));
    expectedMap.put(27, Arrays.asList(3, 60));
    expectedMap.put(30, Arrays.asList(1, 60));
    expectedMap.put(34, Arrays.asList(1, 50));
    expectedMap.put(35, Arrays.asList(2, 40));
    expectedMap.put(38, Arrays.asList(2, 30));
    expectedMap.put(39, Arrays.asList(1, 30));
    expectedMap.put(42, Arrays.asList(2, 20));
    expectedMap.put(45, Arrays.asList(1, 10));
    expectedMap.put(46, Arrays.asList(1, 10));
    expectedMap.put(48, Arrays.asList(1, 0));

    assertEquals(RiskCalculation.calculateRisk(efforts), expectedMap);
  }
}
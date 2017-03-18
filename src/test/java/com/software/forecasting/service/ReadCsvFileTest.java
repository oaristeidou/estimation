package com.software.forecasting.service;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class ReadCsvFileTest {

  @Test
  public void testReadHistoricalData() throws Exception {
    ReadCsvFile readCsvFile = new ReadCsvFile();
    Map<Integer, Set<String>> expectedData = new HashMap<>();
    expectedData.put(1, new HashSet<>(Arrays.asList("x")));
    expectedData.put(2, new HashSet<>(Arrays.asList("y")));
    expectedData.put(3, new HashSet<>(Arrays.asList("x", "y")));
    expectedData.put(4, new HashSet<>(Arrays.asList("-")));
    expectedData.put(5, new HashSet<>(Arrays.asList("z")));
    expectedData.put(6, new HashSet<>(Arrays.asList("x")));
    expectedData.put(7, new HashSet<>(Arrays.asList("-")));
    expectedData.put(8, new HashSet<>(Arrays.asList("y")));
    expectedData.put(9, new HashSet<>(Arrays.asList("y", "x")));

    Map<Integer, Set<String>> readHistoricalData = readCsvFile.readHistoricalData();
    expectedData.forEach(
        (effort, tasks) -> {
          assertEquals(readHistoricalData, expectedData);
        }
    );
  }
}
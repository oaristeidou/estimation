package com.software.forecasting.service;

import com.software.forecasting.model.HistoryDataBean;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class ReadCsvFileTest {

  @Test
  public void testReadHistoricalData() throws Exception {
    List<HistoryDataBean> expectedData = new ArrayList<>();
    expectedData.add(new HistoryDataBean(1, new HashSet<>(Arrays.asList("x"))));
    expectedData.add(new HistoryDataBean(2, new HashSet<>(Arrays.asList("y"))));
    expectedData.add(new HistoryDataBean(3, new HashSet<>(Arrays.asList("x", "y"))));
    expectedData.add(new HistoryDataBean(4, new HashSet<>(Arrays.asList("-"))));
    expectedData.add(new HistoryDataBean(5, new HashSet<>(Arrays.asList("z"))));
    expectedData.add(new HistoryDataBean(6, new HashSet<>(Arrays.asList("x"))));
    expectedData.add(new HistoryDataBean(7, new HashSet<>(Arrays.asList("-"))));
    expectedData.add(new HistoryDataBean(8, new HashSet<>(Arrays.asList("y"))));
    expectedData.add(new HistoryDataBean(9, new HashSet<>(Arrays.asList("y", "x"))));

    List<HistoryDataBean> readHistoricalData = ReadCsvFile.readHistoricalData();
    final int[] index = {0};
    readHistoricalData.forEach(
        (historyData -> {
          assertEquals(historyData.getTasks(), expectedData.get(index[0]).getTasks());
          index[0]++;
        })
    );
  }
}
package com.software.forecasting.service;

import com.google.common.base.Splitter;
import com.software.forecasting.model.HistoryDataBean;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class FileReaderService {

  private static final String fileName = FileReaderService.class.getClassLoader().getResource("docs/historical-data.csv").getPath();

  public static List<HistoryDataBean> readHistoricalData() throws IOException {
    FileReader fileReader = new FileReader(fileName);
    CSVParser historicalDataFileParse = new CSVParser(fileReader, CSVFormat.newFormat(';'));
    List<HistoryDataBean> historicalData = new ArrayList<>();

    historicalDataFileParse.getRecords().forEach(
        (record) -> {
          HistoryDataBean historyDataBean = new HistoryDataBean();
          historyDataBean.setEffort(Integer.parseInt(record.get(0)));

          if (record.size() <= 1){
            historyDataBean.setTasks(new HashSet<>(Collections.singletonList("-")));
          }
          else {
            Splitter googleSplitter = Splitter.on(",").omitEmptyStrings().trimResults();
            Iterable<String> tasks = googleSplitter.split(record.get(1));
            Set<String> normilisedTasks = new HashSet<>();
            tasks.forEach(normilisedTasks::add);
            historyDataBean.setTasks(normilisedTasks);
          }
          historicalData.add(historyDataBean);
        }
    );

    return historicalData;
  }

}

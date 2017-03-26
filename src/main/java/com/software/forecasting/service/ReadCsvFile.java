package com.software.forecasting.service;

import com.google.common.base.Splitter;
import com.software.forecasting.model.FutureTask;
import com.software.forecasting.model.HistoryData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by odyssefs on 18.03.17.
 */
public class ReadCsvFile {

  private static final String fileName = ReadCsvFile.class.getClassLoader().getResource("docs/historical-data.csv").getPath();

  public static Map<Integer, Set<String>> readHistoricalData() throws IOException {
    FileReader fileReader = new FileReader(fileName);
    CSVParser historicalDataFileParse = new CSVParser(fileReader, CSVFormat.newFormat(';'));
    Map<Integer, Set<String>> historicalDatanormilised = new HashMap<>();

    historicalDataFileParse.getRecords().forEach(
        (record) -> {
          HistoryData historyData = new HistoryData();
          historyData.setEffort(Integer.parseInt(record.get(0)));

          if (record.size() <= 1)
            historyData.setTasks(new HashSet<>(Collections.singletonList("-")));
          else {
            Splitter googleSplitter = Splitter.on(",").omitEmptyStrings().trimResults();
            Iterable<String> tasks = googleSplitter.split(record.get(1));
            Set<String> normilisedTasks = new HashSet<>();
            tasks.forEach(normilisedTasks::add);
            historyData.setTasks(normilisedTasks);
          }

          historicalDatanormilised.put(historyData.getEffort(), historyData.getTasks());
        }
    );

    return historicalDatanormilised;
  }

}

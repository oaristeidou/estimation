package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.software.forecasting.model.*;
import com.software.forecasting.service.ReadCsvFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by odyssefs on 25.03.17.
 */
@RestController
public class EstimationApi {

  @RequestMapping(value = "/getEstimationHighChart", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.FOUND)
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart() throws IOException {
    List<FutureTaskBean> futureTaskBeen = new ArrayList<>();
    futureTaskBeen.add(new FutureTaskBean(1, new HashSet<>(Arrays.asList("x"))));
    futureTaskBeen.add(new FutureTaskBean(2, new HashSet<>(Arrays.asList("x", "y"))));
    futureTaskBeen.add(new FutureTaskBean(3, new HashSet<>(Arrays.asList("-"))));

    List<HistoryDataBean> historicalData = ReadCsvFile.readHistoricalData();
    FutureTaskBean.categorise(futureTaskBeen, historicalData);
    List<Integer> efforts = ForecastingSimulation.simulate(futureTaskBeen);

//    Map<Integer, List<Integer>> calculateRisk = RiskCalculation.calculateRisk(efforts);
//
//    List<String> categoriesListString = Lists.transform(efforts, Functions.toStringFunction());

//    Number[] integers = new Number[calculateRisk.size()];
//    Number[] risks = new Number[calculateRisk.size()];
//    List<String> numbers = new ArrayList<>();
//    final int[] index = {0};
//    calculateRisk.forEach(
//        (key, values) -> {
//          numbers.add(String.valueOf(key));
//          integers[index[0]] = values.get(0);
//          risks[index[0]]= values.get(1);
//          index[0]++;
//        }
//    );

    return null;
  }
}

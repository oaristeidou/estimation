package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
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
    Map<Integer, Set<String>> historicalData = ReadCsvFile.readHistoricalData();

    Map<Integer, List<String>> futureTasks = new HashMap<>();
    futureTasks.put(1, new ArrayList<>(Arrays.asList("x")));
    futureTasks.put(2, new ArrayList<>(Arrays.asList("x", "y")));
    futureTasks.put(3, new ArrayList<>(Arrays.asList("-")));
    Map<List<String>, Set<Integer>> categoriseData = FutureTask.categorise(futureTasks, historicalData);

    List<Integer> efforts = ForecastingSimulation.simulate(categoriseData, futureTasks);

    Map<Integer, List<Integer>> calculateRisk = RiskCalculation.calculateRisk(efforts);

    List<String> categoriesListString = Lists.transform(efforts, Functions.toStringFunction());

    Number[] integers = new Number[calculateRisk.size()];
    Number[] risks = new Number[calculateRisk.size()];
    final int[] index = {0};
    calculateRisk.forEach(
        (key, values) -> {
          integers[index[0]] = values.get(0);
          risks[index[0]]= values.get(1);
          index[0]++;
        }
    );

    return new DualAxesOptions(categoriesListString, integers, risks);
  }
}

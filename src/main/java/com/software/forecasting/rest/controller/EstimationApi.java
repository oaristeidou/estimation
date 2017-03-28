package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Splitter;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.software.forecasting.model.*;
import com.software.forecasting.service.*;
import com.software.forecasting.wrapper.DualAxesOptions;
import jdk.nashorn.internal.runtime.JSType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by odyssefs on 25.03.17.
 */
@RestController
public class EstimationApi {

  private final ForecastingSimulationService forecastingSimulationService = new ForecastingSimulationService();
  private final ForecastingCategorizationService forecastingCategorizationService = new ForecastingCategorizationService();
  private final FileReaderService fileReaderService = new FileReaderService();
  private final RiskCalculationService riskCalculationService = new RiskCalculationService();
  private final ParameterParserService parameterParserService = new ParameterParserService();


  @RequestMapping(value = "/estimationHighChart", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.FOUND)
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart(@RequestParam(value = "futureTasks") String tasks, @RequestParam(value = "counts") Integer simulationLoops) throws IOException {
    List<FutureTaskBean> futureTaskBeans = parameterParserService.parseParams(tasks);
    List<HistoryDataBean> historicalData = fileReaderService.readHistoricalData();
    List<FutureTaskBean> futureTasks = forecastingCategorizationService.categorise(futureTaskBeans, historicalData);
    List<Integer> efforts = forecastingSimulationService.simulate(futureTasks, simulationLoops);

    return new DualAxesOptions(riskCalculationService.calculateRisk(efforts, simulationLoops));
  }
}

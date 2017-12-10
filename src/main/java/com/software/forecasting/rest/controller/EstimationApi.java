package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.software.forecasting.model.*;
import com.software.forecasting.service.*;
import com.software.forecasting.wrapper.DualAxesOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by odyssefs on 25.03.17.
 */
@RestController
public class EstimationApi {

  private final ForecastingSimulationService forecastingSimulation = new ForecastingSimulationService();
  private final ForecastingCategorizationService forecastingCategorization = new ForecastingCategorizationService();
  private final FileReaderService fileReaderAdaptor = new FileReaderService();
  private final RiskCalculationService riskCalculation = new RiskCalculationService();
  private final ParameterParserService parameterParser = new ParameterParserService();

  @RequestMapping(value = "/estimationHighChart", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.FOUND)
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart(@RequestParam(value = "futureTasks") String tasks, @RequestParam(value = "counts") Integer simulationLoops) throws IOException {
    List<FutureTaskBean> futureTaskBeans = parameterParser.parseParams(tasks);
    List<HistoryDataBean> historicalData = fileReaderAdaptor.readHistoricalData();
    List<FutureTaskBean> futureTasks = forecastingCategorization.categorise(futureTaskBeans, historicalData);
    List<Integer> efforts = forecastingSimulation.simulate(futureTasks, simulationLoops);
    List<SimulationBean> simulationResultBeans = riskCalculation.calculateRisk(efforts, simulationLoops);

    return new DualAxesOptions(simulationResultBeans);
  }
}

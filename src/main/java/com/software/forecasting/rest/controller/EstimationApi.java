package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.software.forecasting.model.*;
import com.software.forecasting.service.FileReaderService;
import com.software.forecasting.service.ForecastingCategorizationService;
import com.software.forecasting.service.RiskCalculationService;
import com.software.forecasting.wrapper.DualAxesOptions;
import com.software.forecasting.service.ForecastingSimulationService;
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

  private final ForecastingSimulationService forecastingSimulationService;
  private final ForecastingCategorizationService forecastingCategorizationService;
  private final FileReaderService fileReaderService;
  private final RiskCalculationService riskCalculationService;

  @Autowired
  public EstimationApi(ForecastingSimulationService forecastingSimulationService, ForecastingCategorizationService forecastingCategorizationService, FileReaderService fileReaderService, RiskCalculationService riskCalculationService) {
    this.forecastingSimulationService = forecastingSimulationService;
    this.forecastingCategorizationService = forecastingCategorizationService;
    this.fileReaderService = fileReaderService;
    this.riskCalculationService = riskCalculationService;
  }

  @RequestMapping(value = "/estimationHighChart", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.FOUND)
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart(@RequestBody final List<FutureTaskBean> futureTaskBeans) throws IOException {
    int simulationLoop = 20;

    List<HistoryDataBean> historicalData = fileReaderService.readHistoricalData();
    List<FutureTaskBean> futureTasks = forecastingCategorizationService.categorise(futureTaskBeans, historicalData);
    List<Integer> efforts = forecastingSimulationService.simulate(futureTasks, simulationLoop);

    return new DualAxesOptions(riskCalculationService.calculateRisk(efforts));
  }
}

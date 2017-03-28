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

  private final ForecastingSimulationService forecastingSimulationService = new ForecastingSimulationService();
  private final ForecastingCategorizationService forecastingCategorizationService = new ForecastingCategorizationService();
  private final FileReaderService fileReaderService = new FileReaderService();
  private final RiskCalculationService riskCalculationService = new RiskCalculationService();


  @RequestMapping(value = "/estimationHighChart", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.FOUND)
  @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart() throws IOException {
    List<FutureTaskBean> futureTaskBeans = new ArrayList<>();
    futureTaskBeans.add(new FutureTaskBean(1, new HashSet<>(Arrays.asList("x"))));
    futureTaskBeans.add(new FutureTaskBean(2, new HashSet<>(Arrays.asList("x", "y"))));
    futureTaskBeans.add(new FutureTaskBean(3, new HashSet<>(Arrays.asList("-"))));
    int simulationLoop = 20;

    List<HistoryDataBean> historicalData = fileReaderService.readHistoricalData();
    List<FutureTaskBean> futureTasks = forecastingCategorizationService.categorise(futureTaskBeans, historicalData);
    List<Integer> efforts = forecastingSimulationService.simulate(futureTasks, simulationLoop);

    return new DualAxesOptions(riskCalculationService.calculateRisk(efforts));
  }
}

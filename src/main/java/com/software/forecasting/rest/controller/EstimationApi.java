package com.software.forecasting.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.software.forecasting.model.*;
import com.software.forecasting.service.FileReaderService;
import com.software.forecasting.service.RiskCalculationService;
import com.software.forecasting.wrapper.DualAxesOptions;
import com.software.forecasting.service.ForecastingSimulationService;
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
    int simulationLoop = 20;

    List<HistoryDataBean> historicalData = FileReaderService.readHistoricalData();
    FutureTaskBean.categorise(futureTaskBeen, historicalData);
    List<Integer> efforts = ForecastingSimulationService.simulate(futureTaskBeen, simulationLoop);

    return new DualAxesOptions(RiskCalculationService.calculateRisk(efforts));
  }
}

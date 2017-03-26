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
  @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
  public Options getEstimationHighChart() throws IOException {
    Map<Integer, Set<String>> historicalData = ReadCsvFile.readHistoricalData();

    Map<Integer, List<String>> futureTasks = new HashMap<>();
    futureTasks.put(1, new ArrayList<>(Arrays.asList("x")));
    futureTasks.put(2, new ArrayList<>(Arrays.asList("x", "y")));
    futureTasks.put(3, new ArrayList<>(Arrays.asList("-")));
    Map<List<String>, Set<Integer>> categoriseData = FutureTask.categorise(futureTasks, historicalData);

    List<Integer> efforts = ForecastingSimulation.simulate(categoriseData, futureTasks);

    Map<Integer, List<Integer>> calculateRisk = RiskCalculation.calculateRisk(efforts);

    List<String> transform = Lists.transform(efforts, Functions.toStringFunction());

    return new DualAxesOptions();
  }

  private Options createHighChartsOption(List<String> efforts) {
    Options options = new Options();

    options
        .setChart(new ChartOptions()
            .setZoomType(ZoomType.XY));

    options
        .setTitle(new Title("Application Kata: Software Forecasting"));

    options
        .setSubtitle(new Title("Source: https://paper.dropbox.com/doc/Application-Kata-Software-Forecasting-bYEyp2HjiCgWbyaRIVlmQ"));

    EstimationAxis estimationAxis = new EstimationAxis();
    estimationAxis.setCategories(efforts);
    estimationAxis.setCrosshair(Boolean.TRUE);
    options
        .setxAxis(estimationAxis);

    options
        .setyAxis(Arrays.asList(
            new Axis()
                .setLabels(new Labels()
                    .setFormatter(new Function())
                    .setFormat("{value}"))
                .setTitle(new Title("Total Effort")),
            new Axis()
                .setTitle(new Title("Risk"))
                .setLabels(new Labels()
                    .setFormat("{value} %"))
                .setOpposite(Boolean.TRUE))
        );

    options.setTooltip(new Tooltip()
        .setShared(Boolean.TRUE));

    options
        .setLegend(new Legend()
            .setLayout(LegendLayout.VERTICAL)
            .setAlign(HorizontalAlignment.RIGHT)
            .setVerticalAlign(VerticalAlignment.TOP)
            .setX(120)
            .setY(100)
            .setBorderWidth(0)
            .setFloating(Boolean.TRUE));

    options
        .addSeries(new SimpleSeries()
            .setName("Total Effort")
            .setType(SeriesType.COLUMN)
            .setTooltip(new Tooltip()
                .setFormatter(new Function())
                .setValueSuffix(" m"))
            .setData(
                Arrays
                    .asList(new Number[]{1,
                        2,
                        1,
                        1,
                        3,
                        1,
                        1,
                        2,
                        2,
                        1,
                        2,
                        1,
                        1,
                        1})));

    options
        .addSeries(new SimpleSeries()
            .setName("Risk")
            .setType(SeriesType.SPLINE)
            .setyAxis(1)
            .setTooltip(new Tooltip()
                .setFormatter(new Function())
                .setValueSuffix(" mm"))
            .setData(
                Arrays
                    .asList(new Number[]{90,
                        90,
                        80,
                        80,
                        60,
                        60,
                        50,
                        40,
                        30,
                        30,
                        20,
                        10,
                        10,
                        0})));
    return options;
  }
}

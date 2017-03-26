package com.software.forecasting.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;

import java.util.Arrays;

/**
 * Created by odyssefs on 26.03.17.
 */
public class DualAxesOptions extends Options {
  public DualAxesOptions() {


    setChart(new ChartOptions()
        .setZoomType(ZoomType.XY));


    setTitle(new Title("Application Kata: Software Forecasting"));


    setSubtitle(new Title("Source: https://paper.dropbox.com/doc/Application-Kata-Software-Forecasting-bYEyp2HjiCgWbyaRIVlmQ"));

    EstimationAxis estimationAxis = new EstimationAxis();
    estimationAxis.setCategories("15",
        "17",
        "20",
        "24",
        "27",
        "30",
        "34",
        "35",
        "38",
        "39",
        "42",
        "45",
        "46",
        "48");
    estimationAxis.setCrosshair(Boolean.TRUE);

    setxAxis(estimationAxis);


    setyAxis(Arrays.asList(
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

    setTooltip(new Tooltip()
        .setShared(Boolean.TRUE));


    setLegend(new Legend()
        .setLayout(LegendLayout.VERTICAL)
        .setAlign(HorizontalAlignment.RIGHT)
        .setVerticalAlign(VerticalAlignment.TOP)
        .setX(120)
        .setY(100)
        .setBorderWidth(0)
        .setFloating(Boolean.TRUE));


    addSeries(new SimpleSeries()
        .setName("Total Effort")
        .setType(SeriesType.COLUMN)
        .setTooltip(new Tooltip()
            .setFormatter(new Function())
            .setValueSuffix(" times"))
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


    addSeries(new SimpleSeries()
        .setName("Risk")
        .setType(SeriesType.SPLINE)
        .setyAxis(1)
        .setTooltip(new Tooltip()
            .setFormatter(new Function())
            .setValueSuffix(" %"))
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
  }

}

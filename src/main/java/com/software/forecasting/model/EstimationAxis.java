package com.software.forecasting.model;

import com.googlecode.wickedcharts.highcharts.options.Axis;

/**
 * Created by odyssefs on 26.03.17.
 */
public class EstimationAxis extends Axis {
  private Boolean crosshair;

  public Boolean getCrosshair() {
    return crosshair;
  }

  public void setCrosshair(Boolean crosshair) {
    this.crosshair = crosshair;
  }

}

package com.software.forecasting.model;
/**
 * Created by odyssefs on 27.03.17.
 */
public class SimulationResultBean {
  private Integer total;
  private Integer n;
  private Integer risk;

  public SimulationResultBean() {
  }

  public SimulationResultBean(Integer total, Integer n, Integer risk) {
    this.total = total;
    this.n = n;
    this.risk = risk;
  }

  public SimulationResultBean(Integer total, Integer risk) {
    this.total = total;
    this.risk = risk;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getN() {
    return n;
  }

  public void setN(Integer n) {
    this.n = n;
  }

  public Integer getRisk() {
    return risk;
  }

  public void setRisk(Integer risk) {
    this.risk = risk;
  }
}

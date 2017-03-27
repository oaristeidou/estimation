package com.software.forecasting.model;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by odyssefs on 27.03.17.
 */
public class CategoriseBean {
  public FutureTaskBean futureTaskBeanList;
  public Set<Integer> categoryEfforts;

  public CategoriseBean() {
  }

  public CategoriseBean(FutureTaskBean futureTaskBeanList, Set<Integer> categoryEfforts) {
    this.futureTaskBeanList = futureTaskBeanList;
    this.categoryEfforts = categoryEfforts;
  }

  public FutureTaskBean getFutureTaskBeanList() {
    return futureTaskBeanList;
  }

  public void setFutureTaskBeanList(FutureTaskBean futureTaskBeanList) {
    this.futureTaskBeanList = futureTaskBeanList;
  }

  public Set<Integer> getCategoryEfforts() {
    return categoryEfforts;
  }

  public void setCategoryEfforts(Set<Integer> categoryEfforts) {
    this.categoryEfforts = categoryEfforts;
  }
}

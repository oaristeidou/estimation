package com.software.forecasting.service;

import com.google.common.base.Splitter;
import com.software.forecasting.model.FutureTaskBean;
import jdk.nashorn.internal.runtime.JSType;

import java.util.*;

/**
 * Created by odyssefs on 29.03.17.
 */
public class ParameterParserService {

  public List<FutureTaskBean> parseParams(String tasks) {
    List<FutureTaskBean> futureTaskBeans = new ArrayList<>();
    String[] taskStrings = tasks.split("\n");
    for (int i = 0; i< taskStrings.length;i++){
      Splitter semicolonSplitter = Splitter.on(";").omitEmptyStrings().trimResults();
      Iterable<String> futureTaskArray = semicolonSplitter.split(taskStrings[i]);
      FutureTaskBean futureTaskBean = new FutureTaskBean();
      futureTaskArray.forEach(
          futureTask -> {
            if (JSType.isNumber(futureTask))
              futureTaskBean.setEffort(Integer.valueOf(futureTask));
            else {
              Splitter googleSplitter = Splitter.on(",").omitEmptyStrings().trimResults();
              Iterable<String> futureTaskEntries = googleSplitter.split(futureTask);
              Set<String> normilisedTasks = new HashSet<>();
              futureTaskEntries.forEach(task -> {
                if (!task.isEmpty())
                  normilisedTasks.add(task);
              });
              futureTaskBean.setTasks(normilisedTasks);
            }
          }
      );
      if (futureTaskBean.getTasks() == null)
        futureTaskBean.setTasks(new HashSet<>(Arrays.asList("-")));
      futureTaskBeans.add(futureTaskBean);
    }
    return futureTaskBeans;
  }
}

package com.software.forecasting.service;

import com.google.common.collect.Lists;
import com.software.forecasting.model.SimulationBean;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by odyssefs on 20.03.17.
 */
public class RiskCalculationService {

  public List<SimulationBean> calculateRisk(List<Integer> efforts, Integer simulationLoops) {
    List<SimulationBean> simulationPartitionList = createEffortPartitions(efforts, simulationLoops);
    List<SimulationBean> compressedSimulationList = mergeDuplicationEfforts(efforts);
    return makeRiskEffort(compressedSimulationList, simulationPartitionList);
  }

  private static List<SimulationBean> makeRiskEffort(List<SimulationBean> compressedSimulationList, List<SimulationBean> simulationPartitionList) {
    int index = 0;
    List<SimulationBean> simulationPartitionReversedList = Lists.reverse(simulationPartitionList);
    for (SimulationBean simPartition : simulationPartitionReversedList) {
      for (SimulationBean simulation : compressedSimulationList) {
        if (Objects.equals(simPartition.getTotal(), simulation.getTotal()) || index == 0) {
          simulation.setRisk(simPartition.getRisk());
          break;
        } else if (simulation.getRisk() == null) {
          simulation.setRisk(simulationPartitionReversedList.get(index - 1).getRisk());
        }
      }
      index++;
    }
    return compressedSimulationList;
  }

  private List<SimulationBean> mergeDuplicationEfforts(List<Integer> efforts) {
    List<Integer> removeEffortDuplication = new ArrayList<>(new LinkedHashSet(efforts));
    List<SimulationBean> compressedSimulationList = new ArrayList<>();
    removeEffortDuplication.forEach(effort -> compressedSimulationList.add(new SimulationBean(effort, Collections.frequency(efforts, effort), null))
    );
    return compressedSimulationList;
  }

  private List<SimulationBean> createEffortPartitions(List<Integer> efforts, Integer simulationLoops) {
    List<SimulationBean> simulationPartitionList = new ArrayList<>();
    IntStream.range(0, simulationLoops/2).forEach(
        i -> {
          int risk = i * simulationLoops/2;
          double index = (100.0 - risk) / 100 * simulationLoops;
          Integer effort = efforts.get((int) index - 1);
          simulationPartitionList.add(new SimulationBean(effort, risk));
        });
    return simulationPartitionList;
  }
}

package com.software.forecasting.service;

import com.google.common.collect.Lists;
import com.software.forecasting.model.SimulationResultBean;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by odyssefs on 20.03.17.
 */
public class RiskCalculationService {

  public static List<SimulationResultBean> calculateRisk(List<Integer> efforts) {
    List<SimulationResultBean> simulationPartitionList = createEffortPartitions(efforts);
    List<SimulationResultBean> compressedSimulationList = mergeDuplicationEfforts(efforts);
    return makeRiskEffort(compressedSimulationList, simulationPartitionList);
  }

  private static List<SimulationResultBean> makeRiskEffort(List<SimulationResultBean> compressedSimulationList, List<SimulationResultBean> simulationPartitionList) {
    for (SimulationResultBean simPartition : Lists.reverse(simulationPartitionList)) {
      for (SimulationResultBean simulation : compressedSimulationList) {
        if (Objects.equals(simPartition.getTotal(), simulation.getTotal())) {
          simulation.setRisk(simPartition.getRisk());
          break;
        } else if (simulation.getRisk() == null) {
          simulation.setRisk(simPartition.getRisk());
        }
      }
    }
    return compressedSimulationList;
  }

  private static List<SimulationResultBean> mergeDuplicationEfforts(List<Integer> efforts) {
    List<Integer> removeEffortDuplication = new ArrayList<>(new LinkedHashSet(efforts));
    List<SimulationResultBean> compressedSimulationList = new ArrayList<>();
    removeEffortDuplication.forEach(effort -> compressedSimulationList.add(new SimulationResultBean(effort, Collections.frequency(efforts, effort), null))
    );
    return compressedSimulationList;
  }

  private static List<SimulationResultBean> createEffortPartitions(List<Integer> efforts) {
    int partitionRisk = 20;
    List<SimulationResultBean> simulationPartitionList = new ArrayList<>();
    IntStream.range(0, 10).forEach(
        i -> {
          int risk = i * 10;
          double index = (100.0 - risk) / 100 * partitionRisk;
          Integer effort = efforts.get((int) index - 1);
          simulationPartitionList.add(new SimulationResultBean(effort, risk));
        });
    return simulationPartitionList;
  }
}
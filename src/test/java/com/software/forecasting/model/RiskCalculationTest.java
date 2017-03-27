package com.software.forecasting.model;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by odyssefs on 21.03.17.
 */
public class RiskCalculationTest {

  @Test
  public void testCalculateRisk() throws Exception {
    List<Integer> efforts = Arrays.asList(15, 17, 17, 20, 24, 27, 27, 27, 30, 34, 35, 35, 38, 38, 39, 42, 42, 45, 46, 48);
    List<SimulationResultBean> expectedMap = new ArrayList<>();
    expectedMap.add(new SimulationResultBean(15, 1, 90));
    expectedMap.add(new SimulationResultBean(17, 2, 90));
    expectedMap.add(new SimulationResultBean(20, 1, 80));
    expectedMap.add(new SimulationResultBean(24, 1, 80));
    expectedMap.add(new SimulationResultBean(27, 3, 60));
    expectedMap.add(new SimulationResultBean(30, 1, 60));
    expectedMap.add(new SimulationResultBean(34, 1, 50));
    expectedMap.add(new SimulationResultBean(35, 2, 40));
    expectedMap.add(new SimulationResultBean(38, 2, 30));
    expectedMap.add(new SimulationResultBean(39, 1, 30));
    expectedMap.add(new SimulationResultBean(42, 2, 20));
    expectedMap.add(new SimulationResultBean(45, 1, 10));
    expectedMap.add(new SimulationResultBean(46, 1, 10));
    expectedMap.add(new SimulationResultBean(48, 1, 0));

    List<SimulationResultBean> simulationResultBeanList = RiskCalculation.calculateRisk(efforts);
    final int[] index = {0};
    expectedMap.forEach(
        expectedSimulation -> {
          assertEquals(expectedSimulation.getTotal(), simulationResultBeanList.get(index[0]).getTotal());
          assertEquals(expectedSimulation.getN(), simulationResultBeanList.get(index[0]).getN());
          assertEquals(expectedSimulation.getRisk(), simulationResultBeanList.get(index[0]).getRisk());
          index[0]++;
        }
    );
  }
}
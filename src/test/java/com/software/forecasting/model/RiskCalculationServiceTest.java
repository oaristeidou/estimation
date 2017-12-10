package com.software.forecasting.model;

import com.software.forecasting.service.RiskCalculationService;
import mockit.Tested;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by odyssefs on 21.03.17.
 */
public class RiskCalculationServiceTest {

  @Tested
  private RiskCalculationService riskCalculationService;

  @Test
  public void testCalculateRisk() throws Exception {
    List<Integer> efforts = Arrays.asList(15, 17, 17, 20, 24, 27, 27, 27, 30, 34, 35, 35, 38, 38, 39, 42, 42, 45, 46, 48);
    List<SimulationBean> expectedMap = new ArrayList<>();
    expectedMap.add(new SimulationBean(15, 1, 90));
    expectedMap.add(new SimulationBean(17, 2, 90));
    expectedMap.add(new SimulationBean(20, 1, 80));
    expectedMap.add(new SimulationBean(24, 1, 80));
    expectedMap.add(new SimulationBean(27, 3, 60));
    expectedMap.add(new SimulationBean(30, 1, 60));
    expectedMap.add(new SimulationBean(34, 1, 50));
    expectedMap.add(new SimulationBean(35, 2, 40));
    expectedMap.add(new SimulationBean(38, 2, 30));
    expectedMap.add(new SimulationBean(39, 1, 30));
    expectedMap.add(new SimulationBean(42, 2, 20));
    expectedMap.add(new SimulationBean(45, 1, 10));
    expectedMap.add(new SimulationBean(46, 1, 10));
    expectedMap.add(new SimulationBean(48, 1, 0));

    List<SimulationBean> simulationBeanList = riskCalculationService.calculateRisk(efforts, 20);
    final int[] index = {0};
    expectedMap.forEach(
        expectedSimulation -> {
          assertEquals(expectedSimulation.getTotal(), simulationBeanList.get(index[0]).getTotal());
          assertEquals(expectedSimulation.getN(), simulationBeanList.get(index[0]).getN());
          assertEquals(expectedSimulation.getRisk(), simulationBeanList.get(index[0]).getRisk());
          index[0]++;
        }
    );
  }
}
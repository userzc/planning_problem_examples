package com.veureka;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import com.veureka.domain.Product;
import com.veureka.domain.TransportSchedule;
import com.veureka.domain.DemandPoint;

import org.optaplanner.core.config.solver.XmlSolverFactory;
import org.optaplanner.core.api.solver.Solver;

import org.junit.Test;

public class TransportScheduleTest {

  @Test
  public void findBestTransportSchedule() {
    List<Product> products = createProducts();
    List<DemandPoint> demandPoints = createDemandpoints();

    // XmlSolverFactory solverFactory = new XmlSolverFactory();
    // solverFactory.configure("/transportScheduleSolverConfig.xml");
    // Solver solver = solverFactory.buildSolver();

    TransportSchedule initialSolution =
        getInitialSolution(products, demandPoints);

    System.out.println("Initial Schedule: \n" +
                       initialSolution);
    System.out.println("Cost: " +
                       initialSolution.getScore());

    // solver.setPlanningProblem(getInitialSolution(products, demandPoints));
    // solver.solve();

    // TransportSchedule  finalSolution =
    //     (TransportSchedule) solver.getBestSolution();

    // System.out.println("Proposed Schedule: " +
    //                    finalSolution);
    // System.out.println("Cost: " +
    //                    finalSolution.getScore());

  }

  private TransportSchedule getInitialSolution(
      List<Product> products,
      List<DemandPoint> demandPoints) {
    for(Product prod: products){
      prod.setToDP(demandPoints.get(0));
    }
    return new TransportSchedule(products, demandPoints);
  }

  private static List<DemandPoint> createDemandpoints() {
    int demands[] = {0, 10, 25, 45, 15, 5, 15, 20, 15, 10, 20};
    ArrayList<DemandPoint> demandPoints = new ArrayList<DemandPoint>();
    for (int i = 0; i < 10; i++) {
      demandPoints.add(
          new DemandPoint(demands[i + 1], i + 1));
    }
    return demandPoints;
  }

  private static List<Product> createProducts() {
    ArrayList<Product> products = new ArrayList<Product>();
    // Productos que hay en el primer depósito
    for (int j = 0; j < 100; j++) {
      products.add(
          new Product(1));
    }
    // Productos que hay en el segundo depósito
    for (int j = 0; j < 80; j++) {
      products.add(
          new Product(2));
    }
    return products;
  }

}

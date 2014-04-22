package com.veureka;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;

import com.veureka.domain.Product;
import com.veureka.domain.Demand;
import com.veureka.domain.AvailHours;
import com.veureka.domain.MachineScheduleSolution;
import com.veureka.domain.Machine;

import org.optaplanner.core.config.solver.XmlSolverFactory;
import org.optaplanner.core.api.solver.Solver;

import org.junit.Test;

public class MachineScheduleTest {

  @Test
  public void findBestTransportSchedule() {
    List<Product> products = createProducts();
    List<Machine> machines = createMachines();

    XmlSolverFactory solverFactory = new XmlSolverFactory();
    solverFactory.configure("/MachineScheduleSolverConfig.xml");
    Solver solver = solverFactory.buildSolver();

    MachineScheduleSolution initialSolution =
        getInitialSolution(products, machines);


    System.out.println("----------");
    System.out.println("Initial Schedule: \n" +
                       initialSolution);
    System.out.println("----------");
    System.out.println("Cost: " +
                       initialSolution.getScore());
    System.out.println();

    solver.setPlanningProblem(getInitialSolution(products, machines));
    solver.solve();

    MachineScheduleSolution finalSolution =
        (MachineScheduleSolution) solver.getBestSolution();

    System.out.println("----------");
    System.out.println("Proposed Schedule: \n" +
                       finalSolution);
    System.out.println("----------");
    System.out.println("Cost: " +
                       finalSolution.getScore());
    System.out.println();

  }

  private MachineScheduleSolution getInitialSolution(
      List<Product> products,
      List<Machine> machines) {
    for( Product prod: products)
      prod.setInMachine(machines.get(0));
    return new MachineScheduleSolution(products, machines);
  }

  private static List<Machine> createMachines() {
    ArrayList<Machine> machines = new ArrayList<Machine>();
    for (int i = 0; i < AvailHours.vals.length; i++) {
      machines.add(
          new Machine(i+1));
    }
    return machines;
  }

  private static List<Product> createProducts() {
    ArrayList<Product> products = new ArrayList<Product>();
    // Create an array
    for (int i = 0; i < Demand.vals.length; i++) {
      for (int j = 0; j < Demand.vals[i]; j++) {
        products.add(
            new Product(i+1));
      }
    }
    return products;
  }

}

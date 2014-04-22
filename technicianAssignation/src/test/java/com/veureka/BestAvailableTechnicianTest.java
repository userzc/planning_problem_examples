package com.veureka;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import junit.framework.Assert;

import com.veureka.domain.Location;
import com.veureka.domain.ServiceRequest;
import com.veureka.domain.Skill;
import com.veureka.domain.Technician;
import com.veureka.domain.TrainingLevel;
import com.veureka.domain.TechniciansSolution;

import org.optaplanner.core.config.solver.XmlSolverFactory;
import org.optaplanner.core.api.solver.Solver;

import org.junit.Test;

// WARNING: The current implementation does not modifies the "busy"
// status of a technician if its assigned to a request, however I'm
// unaware of a corresponding logic in the Drools' cookbook for the
// moves.
public class BestAvailableTechnicianTest {

  @Test
  public void findBestTechnician() {
    List<Technician> technicians = createTechnicians();
    List<ServiceRequest> requests = createServiceRequests();

    XmlSolverFactory configurer = new XmlSolverFactory();
    configurer.configure("/serviceRequestSolverConfig.xml");
    Solver solver = configurer.buildSolver();

    solver.setPlanningProblem(getInitialSolution(technicians, requests));
    solver.solve();

    TechniciansSolution finalSolution =
        (TechniciansSolution) solver.getBestSolution();


    System.out.println(
        "Selected Technician: " +
        finalSolution.getServiceRequests().get(0).getTechnician());

    Assert.assertEquals(
        technicians.get(4),
        finalSolution.getServiceRequests().get(0).getTechnician());
  }

  private TechniciansSolution getInitialSolution(
      List<Technician> technicians,
      List<ServiceRequest> requests) {
    for (ServiceRequest serviceRequest: requests) {
      serviceRequest.setTechnician(technicians.get(0));
    }
    TechniciansSolution initialSolution =
        new TechniciansSolution(technicians, requests);
    return initialSolution;
  }

  private static List<ServiceRequest> createServiceRequests() {
    return Arrays.asList(
        new ServiceRequest[] {
          new ServiceRequest(Location.SAN_DIEGO, EnumSet.of(Skill.JAVA))});
  }

  private static List<Technician> createTechnicians() {
    return Arrays.asList(
        new Technician[] {
          new Technician(
              Location.WASHINGTON_DC,
              TrainingLevel.JUNIOR,
              false,
              Collections.<Skill> emptySet()),
          new Technician(
              Location.MONTANA,
              TrainingLevel.SEMISENIOR,
              false,
              EnumSet.of(Skill.HADOOP)),
          new Technician(
              Location.NY,
              TrainingLevel.SENIOR,
              true,
              EnumSet.of(Skill.JAVA, Skill.DROOLS)),
          new Technician(
              Location.NORTH_CAROLINA,
              TrainingLevel.SENIOR,
              true,
              EnumSet.of(Skill.JAVA)),
          new Technician(
              Location.NY,
              TrainingLevel.SEMISENIOR,
              false,
              EnumSet.of(Skill.JAVA, Skill.REST)),
          new Technician(
              Location.SAN_DIEGO,
              TrainingLevel.SENIOR,
              false,
              EnumSet.of(Skill.SCALA))
        });
  }

}

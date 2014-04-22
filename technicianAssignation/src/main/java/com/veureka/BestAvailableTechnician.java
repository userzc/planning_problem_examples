package com.veureka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.veureka.domain.Location;
import com.veureka.domain.ServiceRequest;
import com.veureka.domain.Skill;
import com.veureka.domain.Technician;
import com.veureka.domain.TrainingLevel;
import com.veureka.domain.TechniciansSolution;

import org.optaplanner.core.config.solver.XmlSolverFactory;
// import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.Solver;

public class BestAvailableTechnician {
  public static void main(String[] args) {

    // technicians
    List<Technician> technicians = new
        ArrayList<Technician>();
    technicians.add(new Technician(
        Location.WASHINGTON_DC,
        TrainingLevel.JUNIOR,
        false,
        Collections.<Skill> emptySet()));

    technicians.add(new Technician(
        Location.MONTANA,
        TrainingLevel.SEMISENIOR,
        false,
        EnumSet.of(Skill.HADOOP)));

    technicians.add(new Technician(
        Location.NY,
        TrainingLevel.SENIOR,
        true,
        EnumSet.of(Skill.JAVA, Skill.DROOLS)));

    technicians.add(new Technician(
        Location.NORTH_CAROLINA,
        TrainingLevel.SENIOR,
        true,
        EnumSet.of(Skill.JAVA)));

    technicians.add(new Technician(
        Location.NY,
        TrainingLevel.SEMISENIOR,
        false,
        EnumSet.of(Skill.JAVA, Skill.REST)));

    technicians.add(new Technician(
        Location.SAN_DIEGO,
        TrainingLevel.SENIOR,
        false,
        EnumSet.of(Skill.SCALA)));

    // request service
    List<ServiceRequest> requests = new
        ArrayList<ServiceRequest>();

    requests.add(new ServiceRequest(
        Location.SAN_DIEGO,
        EnumSet.of(Skill.JAVA)));

    // Solver configuration

    XmlSolverFactory configurer = new XmlSolverFactory();
    configurer.configure("/serviceRequestSolverConfig.xml");
    Solver solver = configurer.buildSolver();

    for (ServiceRequest serviceRequest: requests) {
      serviceRequest.setTechnician(technicians.get(0));
    }

    // solve using optaplanner
    TechniciansSolution initialSolution = new
        TechniciansSolution(technicians, requests);
    solver.setPlanningProblem(initialSolution);
    solver.solve();

    TechniciansSolution finalSolution =
        (TechniciansSolution) solver.getBestSolution();
    Technician selectedTechnician =
        finalSolution.getServiceRequests().get(0).getTechnician();

    System.out.println("Selected Technician: " +
                       selectedTechnician);

  }
}

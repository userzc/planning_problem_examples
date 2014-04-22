package com.veureka.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.buildin.simple.SimpleScoreDefinition;
import org.optaplanner.core.impl.solution.Solution;

@PlanningSolution
public class TechniciansSolution implements Solution<SimpleScore> {
  private List<Technician> technicians;
  private List<ServiceRequest> serviceRequests;
  private SimpleScore score;

  public TechniciansSolution() {}

  public TechniciansSolution(
      List<Technician> technicians,
      List<ServiceRequest> serviceRequests) {

    this.technicians = technicians;
    this.serviceRequests = serviceRequests;
  }

  public SimpleScore getScore() {
    return score;
  }

  public void setScore(SimpleScore score) {
    this.score = score;
  }

  // this seems to be modifiable, so its based on NQueens
  public Collection<? extends Object> getProblemFacts() {
    List<Object> facts = new ArrayList<Object>();
    facts.addAll(technicians);
    // return technicians;
    return facts;
  }

  // Clonning is no longer necesary, so it's ommited

  // This may be the ValueRangeProvider, but I'm not sure
  @ValueRangeProvider(id = "technicianRange")
  public List<Technician> getTechnicians() {
    return technicians;
  }

  // Check how to do this now
  @PlanningEntityCollectionProperty
  public List<ServiceRequest> getServiceRequests() {
    return serviceRequests;
  }

}

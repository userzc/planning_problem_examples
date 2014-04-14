package com.veureka.domain;

import com.veureka.domain.DemandPoint;
import com.veureka.domain.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleBigDecimalScore;
import org.optaplanner.core.impl.score.buildin.simple.SimpleBigDecimalScoreDefinition;
import org.optaplanner.core.impl.solution.Solution;

// TODO: Use some sort of double score, maybe an int representing up
// to a fixed number of significant digits

@PlanningSolution
public class TransportSchedule implements Solution<SimpleBigDecimalScore>{
  private List<Product> products;
  private List<DemandPoint> demandPoints;
  private SimpleBigDecimalScore score;

  public TransportSchedule() {}

  public TransportSchedule(
      List<Product> products,
      List<DemandPoint> demandPoints) {
    this.products = products;
    this.demandPoints = demandPoints;
  }

  public SimpleBigDecimalScore getScore() {
    return score;
  }

  public void setScore(SimpleBigDecimalScore score) {
    this.score = score;
  }

  public Collections<? extends Object> getProblemFacts() {
    List<Objects> facts = new ArrayList<Objects>();
    facts.addAll(demandPoints);
  }

  @ValueRangeProvider(id = "demandPointsRange")
    public List<DemandPoint> getDemandPoints() {
    return demandPoints;
  }

  @PlanningEntityCollectionProperty
    public List<Product> getProducts() {
    return products;
  }
}

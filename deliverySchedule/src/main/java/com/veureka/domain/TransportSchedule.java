package com.veureka.domain;

import com.veureka.domain.DemandPoint;
import com.veureka.domain.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
// import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;

// Se intenta utilizar este nuevo tipo de score
import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
// import org.optaplanner.core.impl.score.buildin.simple.SimpleBigDecimalScoreDefinition;
import org.optaplanner.core.impl.solution.Solution;

@PlanningSolution
public class TransportSchedule implements Solution<HardSoftBigDecimalScore>{
  private List<Product> products;
  private List<DemandPoint> demandPoints;
  private HardSoftBigDecimalScore score;

  public TransportSchedule() {}

  public TransportSchedule(
      List<Product> products,
      List<DemandPoint> demandPoints) {
    this.products = products;
    this.demandPoints = demandPoints;
  }

  public HardSoftBigDecimalScore getScore() {
    return score;
  }

  public void setScore(HardSoftBigDecimalScore score) {
    this.score = score;
  }

  public Collection<? extends Object> getProblemFacts() {
    List<Object> facts = new ArrayList<Object>();
    facts.addAll(demandPoints);
    facts.add(1);
    facts.add(2);
    return facts;
  }

  @ValueRangeProvider(id = "demandPointsRange")
    public List<DemandPoint> getDemandPoints() {
    return demandPoints;
  }

  @PlanningEntityCollectionProperty
    public List<Product> getProducts() {
    return products;
  }

  public String toString() {
    int count;
    ArrayList<String> msg_list = new ArrayList<String>();
    String msg = "";

    for (int indx = 0; indx < 2; indx++) {
      for (DemandPoint dp: demandPoints) {
        count = 0;
        for (Product prod : products)
          if (prod.getOriginDeposit() == indx + 1 &&
              prod.getToDP() == dp) count++;
        msg_list.add("Deposit " +
                     Integer.toString(indx + 1) +
                     " => " + dp + " : "+
                     Integer.toString(count));
      }
    }

    for (int i = 0; i < 19; i++) {
      msg += msg_list.get(i) + "\n";
    }

    msg += msg_list.get(19);
    return msg;
  }
}

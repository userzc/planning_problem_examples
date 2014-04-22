package com.veureka.domain;

import com.veureka.domain.Machine;
import com.veureka.domain.Product;
import com.veureka.domain.AvailHours;
import com.veureka.domain.Demand;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
// Se intenta utilizar este tipo de score
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.solution.Solution;

@PlanningSolution
public class MachineScheduleSolution implements Solution<HardSoftScore> {
  private List<Product> products;
  private List<Machine> machines;
  private HardSoftScore score;

  public MachineScheduleSolution(
      List<Product> products,
      List<Machine> machines) {
    this.products = products;
    this.machines = machines;
  }

  @ValueRangeProvider(id = "machinesRange")
  public List<Machine> getMachines() {
    return machines;
  }

  public void setMachines(List<Machine> machines) {
    this.machines = machines;
  }

  @PlanningEntityCollectionProperty
  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public MachineScheduleSolution() {}
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }

  public Collection<? extends Object> getProblemFacts() {
    List<Object> facts = new ArrayList<Object>();
    facts.addAll(machines);

    // Perhaps there is a necessity to insert some other objects as in
    // the TransportSchedule case

    for (int i = 0; i < Demand.vals.length; i++) {
      facts.add(i + 1);
    }

    return facts;
  }

  public String toString() {

    int count;
    ArrayList<String> msg_list = new ArrayList<String>();
    String msg = "";

    for (int demanda_indx = 0; demanda_indx < Demand.vals.length; demanda_indx++) {
      for (int hour_indx = 0; hour_indx < AvailHours.vals.length; hour_indx++) {
        count = 0;
        for (Product prod: products)
          if (prod.getTipo() == demanda_indx + 1  &&
              prod.getInMachine().getId() == hour_indx +1) count++;
        msg_list.add("Tipo " +
                     Integer.toString(demanda_indx + 1) +
                     "@" + machines.get(hour_indx) +
                     ": " + Integer.toString(count));

      }
    }

    for (int msg_indx = 0; msg_indx < msg_list.size() - 1; msg_indx++) {
      msg += msg_list.get(msg_indx) + "\n";
    }
    msg += msg_list.get(msg_list.size() - 1);

    return msg;
  }

}

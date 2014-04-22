package com.veureka.domain;

import com.veureka.domain.Machine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Product {

  private int tipo;
  private Machine inMachine;

  public Product() {}

  public Product(int tipo) {
    this.tipo = tipo;
  }

  public Product(int tipo,
                 Machine inMachine) {
    this.tipo = tipo;
    this.inMachine = inMachine;
  }

  @PlanningVariable(valueRangeProviderRefs = "machinesRange")
  public Machine getInMachine() {
    return inMachine;
  }

  public void setInMachine(Machine inMachine) {
    this.inMachine = inMachine;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public String toString() {
    return "ProdTipo: " +
        Integer.toString(tipo)  + "@[" +
        inMachine + "]";
  }

  public int hashCode() {
    return new HashCodeBuilder()
        .append(tipo)
        .append(inMachine)
        .toHashCode();
  }

  public boolean equals(Object obj) {
    if( this == obj) {
      return true;
    } else if (obj instanceof Product) {
      Product other = (Product) obj;
      return new EqualsBuilder()
          .append(tipo, other.tipo)
          .append(inMachine, other.inMachine)
          .isEquals();
    } else {
      return false;
    }
  }

}

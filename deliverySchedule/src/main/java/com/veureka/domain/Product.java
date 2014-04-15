package com.veureka.domain;

import com.veureka.domain.DemandPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Product {
  private int originDeposit;
  private DemandPoint toDP;

  public Product() {}

  public Product(int originDeposit) {
    this.originDeposit = originDeposit;
  }

  public Product(
      int originDeposit,
      DemandPoint toDP) {
    this.originDeposit = originDeposit;
    this.toDP = toDP;
  }

  @PlanningVariable(valueRangeProviderRefs = "demandPointsRange")
  public DemandPoint getToDP() {
    return toDP;
  }

  public void setToDP(DemandPoint toDP) {
    this.toDP = toDP;
  }

  public int getOriginDeposit() {
    return originDeposit;
  }

  public void setOriginDeposit(int originDeposit) {
    this.originDeposit = originDeposit;
  }

  // Perhaps is necessary a constructor from a previous Product
  // object, but not sure

  public String toString() {
    return "ProdOrigin:" +
        Integer.toString(originDeposit) + "=>DP[" + toDP + "]";
  }

  public int hashCode() {
    return new HashCodeBuilder()
        .append(originDeposit)
        .append(toDP)
        .toHashCode();
  }

  public boolean equals(Object obj) {
    if( this == obj) {
      return true;
    } else if (obj instanceof Product) {
      Product other = (Product) obj;
      return new EqualsBuilder()
          .append(originDeposit, other.originDeposit)
          .append(toDP, other.toDP)
          .isEquals();
    } else {
      return false;
    }
  }

}

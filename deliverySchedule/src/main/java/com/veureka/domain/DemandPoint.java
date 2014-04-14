package com.veureka.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class DemandPoint {
  // Required resource quantity
  private int reqRescQuantity;
  private int id;

  public int getReqRescQuantity() {
    return reqRescQuantity;
  }

  public void setReqRescQuantity(int reqRescQuantity) {
    this.reqRescQuantity = reqRescQuantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public DemandPoint() {}

  public DemandPoint(
      int reqRescQuantity,
      int id) {
    this.reqRescQuantity = reqRescQuantity;
    this.id = id;
  }

  public String toString() {
    return "DP: " + Integer.toString(id) + "[" +
        Integer.toString(reqRescQuantity) + "]";
  }

  public int hashCode() {
    HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
    hashCodeBuilder
        .append(id)
        .append(reqRescQuantity);
    return hashCodeBuilder.toHashCode();
  }

  public boolean equals( Object obj) {
    if (this  == obj ) {
      return true;
    } else if (obj instanceof DemandPoint) {
      DemandPoint other = (DemandPoint) obj;
      return new EqualsBuilder()
          .append(id, other.id)
          .append(reqRescQuantity, other.reqRescQuantity)
          .isEquals();
    }
    else {
      return false;
    }
  }

}

package com.veureka.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Machine {
  int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Machine() {}

  public Machine(int id) {
    this.id = id;
  }

  public String toString() {
    return "Machine " + Integer.toString(id);
  }

  public int hashCode() {
    HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
    hashCodeBuilder
        .append(id);
    return hashCodeBuilder.toHashCode();
  }

  public boolean equals(Object obj) {
    if (this  == obj ) {
      return true;
    } else if (obj instanceof Machine) {
      Machine other = (Machine) obj;
      return new EqualsBuilder()
          .append(id, other.id)
          .isEquals();
    }
    else {
      return false;
    }
  }

}

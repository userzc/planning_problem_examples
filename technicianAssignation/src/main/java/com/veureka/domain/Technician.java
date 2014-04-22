package com.veureka.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

// parece que estos son los apropiados para la implementación de los
// métodos faltantes
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Technician {
  private TrainingLevel training;
  private Location location;
  private boolean busy;
  private Set<Skill> skills;

  public Technician() {}

  public TrainingLevel getTrainingLevel() {
    return training;
  }

  public Technician(Location location,
                    TrainingLevel training,
                    boolean busy,
                    Set<Skill> skills) {

    this.location = location;
    this.training = training;
    this.busy = busy;
    this.skills = skills;

  }

  public Technician(Technician technician) {
    this(technician.location,
         technician.training,
         technician.busy,
         (technician.skills.isEmpty())?
         Collections.<Skill> emptySet():
         technician.skills);
  }


  public Set<Skill> getSkills() {
    return skills;
  }
  public void setTraining(TrainingLevel training) {
    this.training = training;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public boolean isBusy() {
    return busy;
  }

  public String toString() {
    return "Tech@" + location.name() + "{" + training.getAbreviation() +
        "}" + "w/:" + Arrays.toString(skills.toArray());
  }

  public int hashCode() {
    HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
    hashCodeBuilder
        .append(location)
        .append(training)
        .append(skills);
    return hashCodeBuilder.toHashCode();
  }

  public boolean equals(Object obj) {
    // Revisar código y preguntar por sugerencias de como hacer esto
    // en caso de que falle
    if (this == obj) {
      return true;
    } else if (obj instanceof Technician) {
      Technician other = (Technician) obj;
      return new EqualsBuilder()
          .append(location, other.location)
          .append(training, other.training)
          .append(skills, other.skills)
          .isEquals();
    } else {
      return false;
    }
  }

}

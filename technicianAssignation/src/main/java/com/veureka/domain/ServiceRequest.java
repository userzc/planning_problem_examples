package com.veureka.domain;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

// parece que estos son los apropiados para la implementación de los
// métodos faltantes
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

// falta por determinar los nombres de los paquetes de optaplanner
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class ServiceRequest {
  private Location location;
  private Set<Skill> neededSkills;
  private Technician technician;

  public ServiceRequest() {}

  public ServiceRequest(Location location,
                        Set<Skill> neededSkills) {

    this.location = location;
    this.neededSkills = neededSkills;
  }

  public ServiceRequest(ServiceRequest serviceRequest) {
    this(serviceRequest.location,
         EnumSet.copyOf(serviceRequest.neededSkills));
    if (serviceRequest.technician != null) {
      setTechnician(new Technician(serviceRequest.technician));
    }
  }

  public Location getLocation() {
    return this.location;
  }

  // TODO: chech how to do this
  // UPDATE: this seems to be the correct way to do it
  @PlanningVariable(valueRangeProviderRefs = "technicianRange" )
  public Technician getTechnician() {
    return technician;
  }

  public void setTechnician(Technician technician) {
    this.technician = technician;
  }

  public Set<Skill> getNeededSkills() {
    return neededSkills;
  }

  public String toString() {
    return "Service {location: " + location.name() + ", requires:" +
        Arrays.toString(neededSkills.toArray()) + ", assigned: " +
        ((technician == null) ? "nobody" : technician.toString()) + "}";

  }


  public int hashCode() {
    // Revisar código y preguntar por sugerencias de como hacer esto
    // en caso de que falle

    return new HashCodeBuilder()
        .append(location)
        .append(neededSkills)
        .append(technician)
        .toHashCode();
  }

  public boolean equals(Object obj) {
    // Revisar código y preguntar por sugerencias de como hacer esto
    // en caso de que falle

    if (this == obj) {
      return true;
    } else if (obj instanceof ServiceRequest) {
      ServiceRequest other = (ServiceRequest) obj;

      return new EqualsBuilder()
          .append(location, other.location)
          .append(neededSkills, other.neededSkills)
          .append(technician, other.technician)
          .isEquals();

    } else {
      return false;
    }
  }

}

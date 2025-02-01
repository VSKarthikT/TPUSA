package com.travelportfolio.TPUSA.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_states")
public class USStates {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "state_code", nullable = false, unique = true)
  private String stateCode;
  @Column(name = "state_name", nullable = false, unique = true)
  private String stateName;
  @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<USCities> cities;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public String getStateCode() {
    return stateCode;
  }

  public String getStateName() {
    return stateName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<USCities> getCities() {
    return cities;
  }

  public void setCities(List<USCities> cities) {
    this.cities = cities;
  }

}

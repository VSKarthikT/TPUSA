package com.travelportfolio.TPUSA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_states")
public class States {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "state_code", nullable = false, unique = true)
  private String stateCode;
  @Column(name = "state_name", nullable = false, unique = true)
  private String stateName;

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
}

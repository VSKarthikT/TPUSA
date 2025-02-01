package com.travelportfolio.TPUSA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "us_cities")
public class USCities {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_state", nullable = false) // Forieng key from USSTATES
  private USStates state;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "county", nullable = false)
  private String county;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "longitude", nullable = false)
  private Double longitude;

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public USStates getState() {
    return state;
  }

  public String getCity() {
    return city;
  }

  public String getCounty() {
    return county;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setState(USStates state) {
    this.state = state;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

}

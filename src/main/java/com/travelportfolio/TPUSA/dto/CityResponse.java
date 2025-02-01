package com.travelportfolio.TPUSA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
  private Long id;
  private String city;
  private String county;
  private Double latitude;
  private Double longitude;
  private String stateName;
  private String stateCode;

}

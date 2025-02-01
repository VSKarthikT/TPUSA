package com.travelportfolio.TPUSA.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Requested JSON from Fronted 
 * {
    "userEmail": "john@example.com",
    "selectedCities": [
        { "cityId": 5, "stateCode": "TX" },
        { "cityId": 7, "stateCode": "TX" },
        { "cityId": 12, "stateCode": "CA" },
        { "cityId": 20, "stateCode": "NY" }
    ]
}

 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTravelRequest {
  private String email;
  private List<CitySelection> slectedCities;

  // SUB Datastructure for request USER
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CitySelection {
    private Long cityId;
    private String stateCode;

  }
}

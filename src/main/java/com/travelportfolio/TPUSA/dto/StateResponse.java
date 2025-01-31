package com.travelportfolio.TPUSA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateResponse {
  private Long id;
  private String StateCode;
  private String StateName;
}

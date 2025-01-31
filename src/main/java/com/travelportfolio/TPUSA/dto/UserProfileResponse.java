package com.travelportfolio.TPUSA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
  private String email;
  private String name;
  private String password;
  private String bio;
  private String profilePicture;
}

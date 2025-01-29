package com.travelportfolio.TPUSA.dto;

import lombok.Data;

@Data
public class UserResponse {
  private String id;
  private String email;
  private String name;
  private String role;
  private String bio;

  // Constructor
  public UserResponse(String email, String name, String role, String bio) {
    this.email = email;
    this.name = name;
    this.role = role;
    this.bio = bio;
  }
}

package com.travelportfolio.TPUSA.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
  // @Size(min = 2, max = 50, message = "Name must be between 2 and 50
  // characters")
  private String name;

  // @Size(max = 255, message = "Bio cannot exceed 255 characters")
  private String bio;

  // @Size(max = 2083, message = "Profile picture URL is too long")
  private String profilePicture;

}

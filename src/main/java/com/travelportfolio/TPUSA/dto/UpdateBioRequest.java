package com.travelportfolio.TPUSA.dto;

import lombok.Data;

@Data
public class UpdateBioRequest {
  private String bio;

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
}

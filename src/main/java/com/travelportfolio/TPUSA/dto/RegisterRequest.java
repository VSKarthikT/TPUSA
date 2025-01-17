package com.travelportfolio.TPUSA.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank(message = "Email is required for registration")
  @Email(message = "Invalid Email Format")
  private String email;
  @NotBlank(message = "Password is required for registration")
  private String password;
  @NotBlank(message = "Name is required for registration")
  private String name;
  // Optional Fields
  private String bio;
  private String profilePicture;
}

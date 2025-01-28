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

  public @NotBlank(message = "Email is required for registration") @Email(message = "Invalid Email Format") String getEmail() {
    return email;
  }

  public void setEmail(@NotBlank(message = "Email is required for registration") @Email(message = "Invalid Email Format") String email) {
    this.email = email;
  }

  public @NotBlank(message = "Password is required for registration") String getPassword() {
    return password;
  }

  public void setPassword(@NotBlank(message = "Password is required for registration") String password) {
    this.password = password;
  }

  public @NotBlank(message = "Name is required for registration") String getName() {
    return name;
  }

  public void setName(@NotBlank(message = "Name is required for registration") String name) {
    this.name = name;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }
}

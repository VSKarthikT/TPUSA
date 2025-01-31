package com.travelportfolio.TPUSA.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank(message = "Email is required for registration")
  @Email(message = "Invalid Email Format")
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid Email Format (must contain a valid domain like .com)")
  private String email;
  @NotBlank(message = "Password is required for registration")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  @Pattern(regexp = "^(?=.*[A-Z]).{6,}$", message = "Password must contain at least one uppercase letter")
  private String password;
  @NotBlank(message = "Name is required for registration")
  @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
  private String name;
  // Optional Fields
  @Size(max = 255, message = "Bio cannot exceed 255 characters")
  private String bio;
  @Size(max = 2083, message = "Profile picture URL is too long")
  private String profilePicture;

  public @NotBlank(message = "Email is required for registration") @Email(message = "Invalid Email Format") String getEmail() {
    return email;
  }

  public void setEmail(
      @NotBlank(message = "Email is required for registration") @Email(message = "Invalid Email Format") String email) {
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

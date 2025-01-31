package com.travelportfolio.TPUSA.controllers;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.dto.UpdateProfileRequest;
import com.travelportfolio.TPUSA.dto.UserProfileResponse;
import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  // GET Profile of user
  @GetMapping("/me")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<UserProfileResponse> getUserProfile(Principal principal) {
    String email = principal.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    UserProfileResponse response = new UserProfileResponse(
        user.getEmail(),
        user.getName(),
        user.getPasswordHash(),
        user.getBio(),
        user.getProfilePicture());
    return ResponseEntity.ok(response);
  }

  // Update Patch
  @PatchMapping("/update")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> updateProfile(@Valid @RequestBody UpdateProfileRequest request, Principal principal) {
    String email = principal.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (request.getName() != null) {
      user.setName(request.getName());
    }
    if (request.getBio() != null) {
      user.setBio(request.getBio());
    }
    if (request.getProfilePicture() != null) {
      user.setProfilePicture(request.getProfilePicture());
    }

    user.setUpdatedAt(LocalDateTime.now());
    userRepository.save(user);

    return ResponseEntity.ok("Profile updated successfully");

  }
}

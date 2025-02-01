package com.travelportfolio.TPUSA.service;

import com.travelportfolio.TPUSA.dto.UpdateProfileRequest;
import com.travelportfolio.TPUSA.dto.UserProfileResponse;
import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // Get user profile by email
  public UserProfileResponse getUserProfile(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    return new UserProfileResponse(
        user.getEmail(),
        user.getName(),
        user.getPasswordHash(),
        user.getBio(),
        user.getProfilePicture());
  }

  // Update user profile
  public String updateUserProfile(String email, UpdateProfileRequest request) {
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

    return "Profile updated successfully";
  }
}

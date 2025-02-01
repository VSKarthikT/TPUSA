package com.travelportfolio.TPUSA.controllers;

import com.travelportfolio.TPUSA.dto.UpdateProfileRequest;
import com.travelportfolio.TPUSA.dto.UserProfileResponse;
import com.travelportfolio.TPUSA.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  // GET User Profile
  @GetMapping("/me")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<UserProfileResponse> getUserProfile(Principal principal) {
    String email = principal.getName();
    UserProfileResponse response = userService.getUserProfile(email);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  // PATCH Update Profile
  @PatchMapping("/update")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> updateProfile(@Valid @RequestBody UpdateProfileRequest request, Principal principal) {
    String email = principal.getName();
    String response = userService.updateUserProfile(email, request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}

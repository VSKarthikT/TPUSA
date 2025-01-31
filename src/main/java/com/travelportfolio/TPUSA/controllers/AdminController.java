package com.travelportfolio.TPUSA.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.dto.UserResponse;
import com.travelportfolio.TPUSA.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<UserResponse> getAllUsers(Authentication authentication) {
    if (authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
      throw new RuntimeException("Access denied: Admins only");
    }
    return userRepository.findAll().stream()
        .map(user -> new UserResponse(user.getId().toString(), user.getEmail(), user.getName(), user.getRole(),
            user.getBio()))
        .collect(Collectors.toList());
  }
}

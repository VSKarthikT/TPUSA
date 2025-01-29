package com.travelportfolio.TPUSA.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.dto.UserResponse;
import com.travelportfolio.TPUSA.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/admin")
public class AdminController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/all_users")
  public List<UserResponse> getAllUsers(Authentication authentication) {

    return userRepository.findAll().stream()
        .map(user -> new UserResponse(user.getEmail(), user.getName(), user.getRole(), user.getBio()))
        .collect(Collectors.toList());
  }
}

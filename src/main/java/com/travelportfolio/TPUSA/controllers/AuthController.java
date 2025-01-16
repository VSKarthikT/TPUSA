package com.travelportfolio.TPUSA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.dto.RegisterRequest;
import com.travelportfolio.TPUSA.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
      return authService.registerUser(request.getEmail(), request.getPassword(), request.getName());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody RegisterRequest request) {
    try {
      return authService.loginUser(request.getEmail(), request.getPassword());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

}

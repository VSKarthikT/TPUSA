package com.travelportfolio.TPUSA.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  // Login a user
  public ResponseEntity<?> loginUser(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found in the database"));
    if (passwordEncoder.matches(password, user.getPasswordHash())) {
      String role = user.getRole();
      return ResponseEntity.ok("Login Successful for role:" + role + "Please access role specific endpoints");
    } else {
      throw new IllegalArgumentException("Incorrect Password");
    }
  }

  // Register a new user
  public ResponseEntity<?> registerUser(String email, String password, String name) {
    if (userRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("Email already present in DB, Try logging in");
    }
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPasswordHash(passwordEncoder.encode(password));
    newUser.setName(name);
    newUser.setRole("ROLE_USER");
    newUser.setCreatedAt(LocalDateTime.now());
    userRepository.save(newUser);
    return ResponseEntity.ok("new User Saved in the database, Login to continue");
  }

}

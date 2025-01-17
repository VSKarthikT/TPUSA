package com.travelportfolio.TPUSA.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  AuthenticationManager authmanager;
  @Autowired
  private JWTService jwtService;
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  // Login a user
  public String loginUser(String email, String password) {

    // User user = userRepository.findByEmail(email);
    Authentication authentication = authmanager
        .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    if (authentication.isAuthenticated()) {
      User user = userRepository.findByEmail(email);
      return jwtService.generateToken(email, user.getRole());
    }
    return "Login Failed";
  }

  // Register a new user
  public ResponseEntity<?> registerUser(String email, String password, String name) {
    if (userRepository.findByEmail(email) != null) {
      throw new IllegalArgumentException("Email already present in DB, Try logging in");
    }
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPasswordHash(encoder.encode(password));
    newUser.setName(name);
    newUser.setRole("ROLE_USER");
    newUser.setCreatedAt(LocalDateTime.now());
    userRepository.save(newUser);
    return ResponseEntity.ok("new User Saved in the database, Login to continue");
  }

}

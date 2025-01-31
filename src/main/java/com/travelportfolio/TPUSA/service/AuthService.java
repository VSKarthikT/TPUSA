package com.travelportfolio.TPUSA.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.travelportfolio.TPUSA.dto.LoginRequest;
import com.travelportfolio.TPUSA.dto.LoginResponse;
import com.travelportfolio.TPUSA.dto.RegisterRequest;
import com.travelportfolio.TPUSA.dto.RegisterResponse;
import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  AuthenticationManager authmanager;
  @Autowired
  private JWTService jwtService;
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Transactional
  public LoginResponse loginUser(String email, String password) {
    try {
      authmanager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password));
      User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      String token = jwtService.generateToken(email, password);
      return new LoginResponse(token, "User Logged In", user.getRole());
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid credentials Try registering");
    }
  }

  // Register a new user
  public ResponseEntity<?> registerUser(RegisterRequest request) {
    String email = request.getEmail();
    String password = request.getPassword();
    String name = request.getName();
    String bio = request.getBio();
    String profile_pic = request.getProfilePicture();
    if (userRepository.findByEmail(email).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new RegisterResponse(email, "Email already exists. Try logging in."));
    }
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setPasswordHash(encoder.encode(password));
    newUser.setName(name);
    newUser.setRole("ROLE_USER");
    newUser.setCreatedAt(LocalDateTime.now());
    if (bio != null) {
      newUser.setBio(bio);
    }
    if (profile_pic != null) {
      newUser.setProfilePicture(profile_pic);
    }
    userRepository.save(newUser);
    RegisterResponse response = new RegisterResponse(email, "User registered successfully. Please log in.");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}

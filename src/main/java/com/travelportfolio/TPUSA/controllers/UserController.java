package com.travelportfolio.TPUSA.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.dto.UpdateBioRequest;
import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/updateBio")
  public ResponseEntity<?> updateBio(@RequestBody UpdateBioRequest request, Principal principal) {
    String email = principal.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    user.setBio(request.getBio());
    userRepository.save(user);
    return ResponseEntity.ok("Bio Updated Successfully");
  }

}

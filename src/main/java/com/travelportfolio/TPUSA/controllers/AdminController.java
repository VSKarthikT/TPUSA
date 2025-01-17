package com.travelportfolio.TPUSA.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/admin")
public class AdminController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/all_users")
  public List<User> getAllUsers(Authentication authentication) {

    return userRepository.findAll();
  }
}

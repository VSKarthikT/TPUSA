package com.travelportfolio.TPUSA.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class samplecontroller {
  @GetMapping("/")
  public String getMethodName() {
    return "Use Login/Register API'S to authenticate USER and get JWT Token";
  }
}

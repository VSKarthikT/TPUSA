package com.travelportfolio.TPUSA.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class samplecontroller {
  @GetMapping("/default")
  public String getMethodName() {
    return "Hello World!";
  }
}

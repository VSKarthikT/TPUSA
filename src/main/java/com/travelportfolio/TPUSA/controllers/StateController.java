package com.travelportfolio.TPUSA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travelportfolio.TPUSA.service.StateService;

@RestController
@RequestMapping("api/v1/places/states")
public class StateController {
  @Autowired
  private StateService stateService;

  // All states
  @GetMapping("/all")
  public ResponseEntity<?> getAllStates() {
    return stateService.getAllStates();
  }

  @GetMapping("/id/{state_id}")
  public ResponseEntity<?> getById(@PathVariable Long state_id) {
    return stateService.getStateById(state_id);
  }

  @GetMapping("/code/{state_code}")
  public ResponseEntity<?> getByStateCode(@PathVariable String state_code) {
    return stateService.getStateByCode(state_code);
  }

  @GetMapping("/name/{state_name}")
  public ResponseEntity<?> getByStateName(@PathVariable String state_name) {
    return stateService.getStateByName(state_name);
  }
}

package com.travelportfolio.TPUSA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.dto.StateResponse;
import com.travelportfolio.TPUSA.model.States;
import com.travelportfolio.TPUSA.repository.StatesRepository;

@Service
public class StateService {
  @Autowired
  private StatesRepository statesRepository;

  public List<States> getAllStates() {
    return statesRepository.findAll();
  }

  public ResponseEntity<?> getStateById(Long Id) {
    Optional<States> States = statesRepository.findById(Id);
    if (States.isPresent()) {
      StateResponse response = new StateResponse(
          States.get().getId(),
          States.get().getStateCode(),
          States.get().getStateName());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check State ID we have 50 States");
    }
  }

  public ResponseEntity<?> getStateByCode(String StateCode) {
    Optional<States> States = statesRepository.findByStateCode(StateCode.toUpperCase());
    if (States.isPresent()) {
      StateResponse response = new StateResponse(
          States.get().getId(),
          States.get().getStateCode(),
          States.get().getStateName());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong State CODE");
    }
  }

  public ResponseEntity<?> getStateByName(String StateName) {
    Optional<States> States = statesRepository.findByStateName(StateName);
    if (States.isPresent()) {
      StateResponse response = new StateResponse(
          States.get().getId(),
          States.get().getStateCode(),
          States.get().getStateName());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong State Name");
    }
  }
}

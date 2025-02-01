package com.travelportfolio.TPUSA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.dto.StateResponse;
import com.travelportfolio.TPUSA.model.USStates;
import com.travelportfolio.TPUSA.repository.StatesRepository;

@Service
public class StateService {
  @Autowired
  private StatesRepository statesRepository;

  public ResponseEntity<List<StateResponse>> getAllStates() {
    List<StateResponse> stateResponses = statesRepository.findAll()
        .stream()
        .map(state -> new StateResponse(
            state.getId(),
            state.getStateCode(),
            state.getStateName()))
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(stateResponses);
  }

  public ResponseEntity<?> getStateById(Long Id) {
    Optional<USStates> States = statesRepository.findById(Id);
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
    Optional<USStates> States = statesRepository.findByStateCode(StateCode.toUpperCase());
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
    Optional<USStates> States = statesRepository.findByStateName(StateName);
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

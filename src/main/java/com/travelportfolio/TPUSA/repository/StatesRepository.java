package com.travelportfolio.TPUSA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelportfolio.TPUSA.model.USStates;

@Repository
public interface StatesRepository extends JpaRepository<USStates, Long> {

  Optional<USStates> findByStateName(String stateName);

  Optional<USStates> findByStateCode(String stateCode);
}

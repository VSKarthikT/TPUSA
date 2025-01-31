package com.travelportfolio.TPUSA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelportfolio.TPUSA.model.States;

@Repository
public interface StatesRepository extends JpaRepository<States, Long> {
  Optional<States> findByStateName(String stateName);

  Optional<States> findByStateCode(String stateCode);
}

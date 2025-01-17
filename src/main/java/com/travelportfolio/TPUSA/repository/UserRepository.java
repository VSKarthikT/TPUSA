package com.travelportfolio.TPUSA.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelportfolio.TPUSA.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByEmail(String email);
}

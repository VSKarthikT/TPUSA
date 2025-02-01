package com.travelportfolio.TPUSA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travelportfolio.TPUSA.model.USCities;
import com.travelportfolio.TPUSA.model.USStates;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<USCities, Long> {
  Page<USCities> findAll(Pageable page);

  // Find all cities in a given state ID(1-alaska and similar )
  Page<USCities> findByState(USStates state, Pageable page);

  // Find cities by name (case insensitive)
  @Query(value = "SELECT * FROM us_cities WHERE city ILIKE :city ORDER BY id ASC LIMIT :limit", nativeQuery = true)
  List<USCities> findByCityIgnoreCase(@Param("city") String city, @Param("limit") int limit);

  // Find all cities in a given county
  List<USCities> findByCountyIgnoreCase(String county);

}

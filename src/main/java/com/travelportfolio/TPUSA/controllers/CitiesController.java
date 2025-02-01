package com.travelportfolio.TPUSA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelportfolio.TPUSA.service.CityService;

@RestController
@RequestMapping("api/v1/places/states/cities")
public class CitiesController {
  @Autowired
  private CityService cityService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllCities(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(cityService.getAllCities(page, size));
  }

  @GetMapping("/state/{stateId}")
  public ResponseEntity<?> getAllCitiesByState(
      @PathVariable Long stateId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return cityService.getCitiesByState(stateId, page, size);
  }

  @GetMapping("/city/{cityname}")
  public ResponseEntity<?> getAllCitiesByName(
      @PathVariable String cityname,
      @RequestParam(defaultValue = "10") int limit) {

    return cityService.getCitiesByName(cityname, limit);
  }

}

package com.travelportfolio.TPUSA.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.dto.CityResponse;
import com.travelportfolio.TPUSA.model.USCities;
import com.travelportfolio.TPUSA.model.USStates;
import com.travelportfolio.TPUSA.repository.CitiesRepository;
import com.travelportfolio.TPUSA.repository.StatesRepository;

@Service
public class CityService {

  @Autowired
  private CitiesRepository citiesRepository;
  @Autowired
  private StatesRepository statesRepository;

  public ResponseEntity<Page<CityResponse>> getAllCities(int page, int size) {
    Page<USCities> cityPage = citiesRepository.findAll(PageRequest.of(page, size));
    Page<CityResponse> cityResponses = cityPage
        .map(city -> new CityResponse(
            city.getId(),
            city.getCity(),
            city.getCounty(),
            city.getLatitude(),
            city.getLongitude(),
            city.getState().getStateName(),
            city.getState().getStateCode()));
    return ResponseEntity.status(HttpStatus.OK).body(cityResponses);

  }

  public ResponseEntity<?> getCitiesByState(Long stateId, int page, int size) {
    Optional<USStates> state = statesRepository.findById(stateId);
    if (state.isPresent()) {
      Page<USCities> citiesPage = citiesRepository.findByState(state.get(), PageRequest.of(page, size));
      Page<CityResponse> response = citiesPage
          .map(city -> new CityResponse(
              city.getId(),
              city.getCity(),
              city.getCounty(),
              city.getLatitude(),
              city.getLongitude(),
              city.getState().getStateName(),
              city.getState().getStateCode()));
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State details not found");
    }
  }

  public ResponseEntity<?> getCitiesByName(String cityname, int limit) {

    List<USCities> cities = citiesRepository.findByCityIgnoreCase(cityname, limit);
    if (cities.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City name not found");
    } else {
      List<CityResponse> respose = cities
          .stream()
          .map(city -> new CityResponse(
              city.getId(),
              city.getCity(),
              city.getCounty(),
              city.getLatitude(),
              city.getLongitude(),
              city.getState().getStateName(),
              city.getState().getStateCode()))
          .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.OK).body(respose);

    }

  }

}

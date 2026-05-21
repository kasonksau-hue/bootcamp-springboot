package com.bootcamp.demo.bc_mtr_station.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.bc_mtr_station.dto.EarliestTrainDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.MTRDto;

public interface MTROperation {
  @GetMapping(value = "/mtr/schedule")
  MTRDto getSchedule(@RequestParam String line, @RequestParam String sta);

  @GetMapping(value = "/mtr/stations")
  Map<String, List<String>> getAllStations();

  @GetMapping(value = "/mtr/stations/{line}")
  List<StationEntity> getStations(@PathVariable String line);

  @GetMapping(value = "/mtr/earliest")
  EarliestTrainDto getEarliestTrain(@RequestParam String sta);

  @GetMapping(value = "mtr/linesignal")
  LineSignalDto getLineSignal(@RequestParam String line);
  

  // @GetMapping(value = "/mtr/lines")
  // List<StationEntity> getAllLines();

  // @DeleteMapping(value = "/mtr/station")
  // void deleteStation(@RequestParam String sta);


}
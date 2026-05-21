package com.bootcamp.demo.bc_mtr_station.service;

import java.util.List;
import java.util.Optional;
import com.bootcamp.demo.bc_mtr_station.dto.EarliestTrainDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.MTRDto;

public interface MTRService {
  MTRDto getSchedule(String line, String TKO);

  List<StationEntity> getStations(String line);

  List<LineStationEntity> getAllLineStations();

  Optional<LineEntity> getLineByName(String name); // with cache

  EarliestTrainDto getEarliestTrain(String sta);

  // LineSignalDto getSignalDto (String sta); 

  LineSignalDto getLineSignal (String line); 
}
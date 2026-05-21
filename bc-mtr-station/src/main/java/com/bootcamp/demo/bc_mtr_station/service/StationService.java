package com.bootcamp.demo.bc_mtr_station.service;

import java.util.List;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;

public interface StationService {
  List<StationEntity> insert(List<StationEntity> stationEntities);
  StationEntity insert(StationEntity stationEntity);
  LineStationEntity insert(LineStationEntity lineStationEntity);
  void deleteAll();
}
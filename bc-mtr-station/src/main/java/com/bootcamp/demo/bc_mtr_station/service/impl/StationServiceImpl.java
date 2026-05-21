package com.bootcamp.demo.bc_mtr_station.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.repository.StationRepository;
import com.bootcamp.demo.bc_mtr_station.service.StationService;

@Service
public class StationServiceImpl implements StationService {
  @Autowired
  private StationRepository stationRepository;
  @Autowired
  private LineStationRepository lineStationRepository;

  @Override
  public List<StationEntity> insert(List<StationEntity> stationEntities) {
    return this.stationRepository.saveAll(stationEntities);
  }

  @Override
  public StationEntity insert(StationEntity stationEntity) {
    return this.stationRepository.save(stationEntity);
  }

  @Override
  public LineStationEntity insert(LineStationEntity lineStationEntity) {
    return this.lineStationRepository.save(lineStationEntity);
  }

  @Override
  public void deleteAll() {
    this.lineStationRepository.deleteAll();
    this.stationRepository.deleteAll();
  }
}
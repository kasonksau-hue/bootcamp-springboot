package com.bootcamp.demo.bc_mtr_station.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_mtr_station.controller.MTROperation;
import com.bootcamp.demo.bc_mtr_station.dto.EarliestTrainDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.model.MTRDto;
import com.bootcamp.demo.bc_mtr_station.service.MTRService;

@RestController
public class MTRController implements MTROperation {
  @Autowired
  private MTRService mtrService;

  @Override
  public MTRDto getSchedule(String line, String sta) {
    return mtrService.getSchedule(line, sta);
  }

  @Override
  public Map<String, List<String>> getAllStations() {
    Map<String, List<String>> mtrMap = new HashMap<>();
    List<LineStationEntity> lineStationEntities =
        this.mtrService.getAllLineStations();
    for (LineStationEntity lineStationEntity : lineStationEntities) {
      String lineName = lineStationEntity.getLineName();
      List<String> stations = mtrMap.getOrDefault(lineName, new ArrayList<>());
      stations.add(lineStationEntity.getStationName());
      mtrMap.put(lineName, stations);
    }
    return mtrMap;
  }

  @Override
  public List<StationEntity> getStations(String line) {
    return this.mtrService.getStations(line);
  }

  @Override
  public EarliestTrainDto getEarliestTrain(@RequestParam String sta) {
    return this.mtrService.getEarliestTrain(sta);
  }

  @Override
  public LineSignalDto getLineSignal(@RequestParam String line) {
    return this.mtrService.getLineSignal(line);
  }
}
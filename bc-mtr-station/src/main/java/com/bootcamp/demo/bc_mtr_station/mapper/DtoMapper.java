package com.bootcamp.demo.bc_mtr_station.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_mtr_station.dto.EarliestTrainDto;
import com.bootcamp.demo.bc_mtr_station.model.MTRDto;

@Component
public class DtoMapper {
  public EarliestTrainDto.Train map(MTRDto.Schedule.Train train, String direction) {
    return EarliestTrainDto.Train.builder() //
      .arrivalTime(train.getTime()) //
      .destination(train.getDest()) //
      .direction(direction) //
      .build();
  }

  //   public LineSignalDto.Line map(MTRDto.Schedule. train, String direction) {
  //   return EarliestTrainDto.Train.builder() //
  //     .arrivalTime(train.getTime()) //
  //     .destination(train.getDest()) //
  //     .direction(direction) //
  //     .build();
  // }
}
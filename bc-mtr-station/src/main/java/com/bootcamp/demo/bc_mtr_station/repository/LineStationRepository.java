package com.bootcamp.demo.bc_mtr_station.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;

public interface LineStationRepository extends JpaRepository<LineStationEntity, Long> {
  List<LineStationEntity> findByLineEntity(LineEntity lineEntity);
  List<LineStationEntity> findByStationName(String sta);
}
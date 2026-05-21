package com.bootcamp.demo.bc_mtr_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, Long> {

}
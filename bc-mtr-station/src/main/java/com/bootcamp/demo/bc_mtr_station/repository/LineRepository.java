package com.bootcamp.demo.bc_mtr_station.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;

public interface LineRepository extends JpaRepository<LineEntity, Long> {
  Optional<LineEntity> findByName(String name);
}
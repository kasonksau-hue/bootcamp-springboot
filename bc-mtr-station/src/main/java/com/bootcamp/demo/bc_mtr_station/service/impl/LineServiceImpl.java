package com.bootcamp.demo.bc_mtr_station.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.service.LineService;

@Service
public class LineServiceImpl implements LineService {
  @Autowired
  private LineRepository lineRepository;

  @Override
  public List<LineEntity> insert(List<LineEntity> lineEntities) {
    return this.lineRepository.saveAll(lineEntities);
  }

  @Override
  public LineEntity insert(LineEntity lineEntity) {
    return this.lineRepository.save(lineEntity);
  }

  @Override
  public void deleteAll() {
    this.lineRepository.deleteAll();
  } 
}
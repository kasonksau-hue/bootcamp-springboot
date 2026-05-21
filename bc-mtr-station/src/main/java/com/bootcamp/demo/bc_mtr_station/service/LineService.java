package com.bootcamp.demo.bc_mtr_station.service;

import java.util.List;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;

public interface LineService {
  List<LineEntity> insert(List<LineEntity> lineEntities);
  LineEntity insert(LineEntity lineEntity);
  void deleteAll();
}
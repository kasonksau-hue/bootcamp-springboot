package com.bootcamp.demo.bc_mtr_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "desc_eng", nullable = false)
  private String descEng;
  @Column(name = "desc_chin", nullable = false)
  private String descChin;
}
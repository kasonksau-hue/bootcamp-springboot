package com.bootcamp.demo.bc_mtr_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Line_stations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LineStationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String stationName;
  @Column(nullable = false)
  private String lineName;

  @ManyToOne
  @JoinColumn(name = "line_id", nullable = false)
  @Setter
  private LineEntity lineEntity;

  @ManyToOne
  @JoinColumn(name = "station_id", nullable = false)
  @Setter
  private StationEntity stationEntity;
}
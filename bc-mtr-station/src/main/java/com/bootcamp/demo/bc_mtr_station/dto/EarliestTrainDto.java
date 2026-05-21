package com.bootcamp.demo.bc_mtr_station.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EarliestTrainDto {
  @JsonProperty(value = "sys_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime sysTime;

  @JsonProperty(value = "curr_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime currTime;

  private String currentStation;

  private List<Train> trains;

  @Getter
  @Builder
  public static class Train {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    private String direction;
    private String destination;
  }
}
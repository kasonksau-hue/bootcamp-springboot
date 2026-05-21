package com.bootcamp.demo.bc_mtr_station.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LineSignalDto {

  private String line;
  private String station;
  private String signal;
  private List<String> delayStations;

  @JsonProperty(value = "sys_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime sysTime;

  @JsonProperty(value = "curr_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime currTime;

  
//   @Getter
//   @Builder
//   @ToString
//   public static class Line {
//     private String signal;

// }
}
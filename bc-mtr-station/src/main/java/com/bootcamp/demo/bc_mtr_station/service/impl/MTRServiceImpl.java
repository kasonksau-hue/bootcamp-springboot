package com.bootcamp.demo.bc_mtr_station.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.bc_mtr_station.dto.EarliestTrainDto;
import com.bootcamp.demo.bc_mtr_station.dto.LineSignalDto;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.lib.RedisHelper;
import com.bootcamp.demo.bc_mtr_station.mapper.DtoMapper;
import com.bootcamp.demo.bc_mtr_station.model.MTRDto;
import com.bootcamp.demo.bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.demo.bc_mtr_station.service.MTRService;

@Service
public class MTRServiceImpl implements MTRService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LineRepository lineRepository;

  @Autowired
  private LineStationRepository lineStationRepository;

  // @Autowired
  // private RedisTemplate<String, String> redisTemplate;

  // @Autowired
  // private ObjectMapper objectMapper;

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private DtoMapper dtoMapper;


  // https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php?line=TKL&sta=TKO
  @Value("${api.gov.domain}")
  private String domain;

  @Value("${api.gov.version}")
  private String vers;

  @Value("${api.gov.mtr.schedule}")
  private String path;

  @Override
  public MTRDto getSchedule(String line, String sta) {
    String url = UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(this.domain) //
        .pathSegment(this.vers) //
        .path(this.path) //
        .queryParam("line", line).queryParam("sta", sta).build() //
        .toUriString();
    System.out.println("url=" + url);
    return this.restTemplate.getForObject(url, MTRDto.class);
  }

  // ! Re-write service become cache (redis)
  @Override
  public List<StationEntity> getStations(String line) {
    // Cache Pattern: Read-Through
    // ! Step 1 Read Cache (Redis)
    String lineKey = "line_" + line;
    String stationKey = "sta_" + line;
    LineEntity lineEntity = this.redisHelper.get(lineKey, LineEntity.class);
    LineStationEntity[] lineStationEntities =
        this.redisHelper.get(stationKey, LineStationEntity[].class);
    if (lineEntity != null && lineStationEntities != null) {
      return Arrays.stream(lineStationEntities) //
          .map(e -> e.getStationEntity()) //
          .collect(Collectors.toList());
    } else { // ! Redis Not Found.
      // ! Step 2 Read DB
      Optional<LineEntity> oLineEntity = this.lineRepository.findByName(line);
      if (oLineEntity.isPresent()) {
        List<LineStationEntity> stationEntities =
            this.lineStationRepository.findByLineEntity(oLineEntity.get());
        // ! Step 3 write back redis
        this.redisHelper.set(lineKey, oLineEntity.get(),
            Duration.ofMinutes(1L));
        this.redisHelper.set(stationKey, stationEntities,
            Duration.ofMinutes(1L));
        return stationEntities.stream() //
            .map(e -> e.getStationEntity()) //
            .collect(Collectors.toList());
      } else {
        throw new RuntimeException("Line Not Found.");
      }
    }
  }

  @Override
  public Optional<LineEntity> getLineByName(String name) {
    return null;
  }

  @Override
  public List<LineStationEntity> getAllLineStations() {
    return this.lineStationRepository.findAll();
  }

  @Override
  public EarliestTrainDto getEarliestTrain(String sta) {
    List<LineStationEntity> lineStationEntities =
        this.lineStationRepository.findByStationName(sta);
    if (lineStationEntities.isEmpty())
      throw new RuntimeException("Station Not Found."); // TBC.

    List<EarliestTrainDto.Train> earliestTrains = new ArrayList<>();
    LocalDateTime currentTime = null;
    LocalDateTime sysTime = null;

    for (LineStationEntity lineStation : lineStationEntities) {
      String line = lineStation.getLineName();
      MTRDto mtrDto = this.getSchedule(line, sta);
      MTRDto.Schedule schedule = mtrDto.getData().get(line + "-" + sta);

      sysTime = mtrDto.getSysTime();
      currentTime = mtrDto.getCurrTime();

      Set<String> upDests = new HashSet<>();
      List<MTRDto.Schedule.Train> upTrains = schedule.getUpTrains();
      for (MTRDto.Schedule.Train train : upTrains) {
        upDests.add(train.getDest());
      }

      Set<String> downDests = new HashSet<>();
      List<MTRDto.Schedule.Train> downTrains = schedule.getDownTrains();
      for (MTRDto.Schedule.Train train : downTrains) {
        downDests.add(train.getDest());
      }

      // ! Loop HashSet
      for (String dest : upDests) {
        MTRDto.Schedule.Train train = upTrains.stream() //
            .filter(e -> e.getDest().equals(dest)) //
            .sorted((t1, t2) -> t1.getTime().isBefore(t2.getTime()) ? -1 : 1)
            .findFirst() //
            .orElseThrow(() -> new RuntimeException("xxx")); // TBC
        EarliestTrainDto.Train earliestTrain = this.dtoMapper.map(train, "UP");
        earliestTrains.add(earliestTrain);
      }

      for (String dest : downDests) {
        MTRDto.Schedule.Train train = downTrains.stream() //
            .filter(e -> e.getDest().equals(dest)) //
            .sorted((t1, t2) -> t1.getTime().isBefore(t2.getTime()) ? -1 : 1)
            .findFirst() //
            .orElseThrow(() -> new RuntimeException("xxx")); // TBC
        EarliestTrainDto.Train earliestTrain =
            this.dtoMapper.map(train, "DOWN");
        earliestTrains.add(earliestTrain);
      }
    }
    return EarliestTrainDto.builder() //
        .sysTime(sysTime) //
        .currTime(currentTime) //
        .trains(earliestTrains) //
        .currentStation(sta) //
        .build();
  }

  @Override
  public LineSignalDto getLineSignal(String line) {
    List<LineStationEntity> lineStationEntities =
        this.lineStationRepository.findByStationName(line);
    if (lineStationEntities.isEmpty())
      throw new RuntimeException("Line Not Found."); // TBC.

    List<String> delayStations = new ArrayList<>();
    LocalDateTime currentTime = null;
    LocalDateTime sysTime = null;
    int delayCount = 0;

    for (LineStationEntity lineStationEntity : lineStationEntities) {
      String sta = lineStationEntity.getStationName();
      MTRDto mtrDto = this.getSchedule(line, sta);
      currentTime = mtrDto.getCurrTime();
      sysTime = mtrDto.getSysTime();
      if (mtrDto.getIsDelay().equals("Y")){
        delayStations.add(sta);
        delayCount++;
      } 
    }

        String signal = "";

        if (delayCount == 0){
          signal = "GREEN";
          }
          else if (delayCount == 1){
            signal = "YELLOW";
          }
            else if (delayCount >1){
              signal = "RED";
            } 

        LineSignalDto signalDto = LineSignalDto.builder()
                .line(line)
                .signal(signal)
                .delayStations(delayStations)
                .currTime(currentTime)
                .sysTime(sysTime)
                .build();

    return signalDto;
    }


    //     lineSignals.add(lineSignal);
 

    // LineSignalDto dto = new LineSignalDto();
    // dto.setLine("TKL");
    // dto.setSignal(signal);
    // dto.setDelayStations(delayStations);
    // dto.setCurr_time(response.getCurr_time());
    // dto.setSys_time(response.getSys_time());


  }




package com.bootcamp.demo.bc_mtr_station.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.demo.bc_mtr_station.entity.StationEntity;
import com.bootcamp.demo.bc_mtr_station.service.LineService;
import com.bootcamp.demo.bc_mtr_station.service.StationService;

@Component
public class AppStarter implements CommandLineRunner {
  @Autowired
  private LineService lineService;
  @Autowired
  private StationService stationService;

  private LineEntity createLine(String name, String description) {
    // ! Create Line -> insert line
    LineEntity lineEntity =
        LineEntity.builder().name(name).description(description).build();
    this.lineService.insert(lineEntity);
    return lineEntity;
  }

  private void createStation(String name, String descEng, String descChin,
      LineEntity... lineEntities) {
    // ! Create Station -> insert station
    StationEntity stationEntity = StationEntity.builder().name(name)
        .descEng(descEng).descChin(descChin).build();
    this.stationService.insert(stationEntity);
    // ! Loop the lines -> insert line_station(s)
    for (LineEntity lineEntity : lineEntities) {
      LineStationEntity lineStationEntity = LineStationEntity.builder()
          .stationName(name).lineName(lineEntity.getName()).build();
      lineStationEntity.setLineEntity(lineEntity);
      lineStationEntity.setStationEntity(stationEntity);
      this.stationService.insert(lineStationEntity);
    }
  }

  @Override
  public void run(String... args) throws Exception {
    // clear all
    this.stationService.deleteAll();
    this.lineService.deleteAll();
    LineEntity ael = createLine("AEL", "Airport Express");
    LineEntity tcl = createLine("TCL", "Tung Chung Line");
    LineEntity tml = createLine("TML", "Tuen Ma Line");
    LineEntity tkl = createLine("TKL", "Tseung Kwan O Line");
    LineEntity eal = createLine("EAL", "East Rail Line");
    LineEntity sil = createLine("SIL", "South Island Line");
    LineEntity twl = createLine("TWL", "Tsuen Wan Line");
    LineEntity isl = createLine("ISL", "Island Line");
    LineEntity ktl = createLine("KTL", "Kwun Tong Line");
    LineEntity drl = createLine("DRL", "Disneyland Resort Line");

    // Previous/Next -> 2 SQL Approaches (Other Approach: Graph Database Many-to-Many)
    // 1. seq
    // 2. PrevSta + NextSta
    createStation("HOK", "Hong Kong", "香港", ael, tcl);
    createStation("KOW", "Kowloon", "九龍", ael, tcl);
    createStation("TSY", "Tsing Yi", "青衣", ael, tcl);
    createStation("AIR", "Airport", "機場", ael);
    createStation("AWE", "AsiaWorld Expo", "博覽館", ael);

    createStation("OLY", "Olympic", "奧運", tcl);
    createStation("NAC", "Nam Cheong", "南昌", tcl, tml);
    createStation("LAK", "Lai King", "荔景", tcl, twl);
    createStation("SUN", "Sunny Bay", "欣澳", tcl, drl);
    createStation("TUC", "Tung Chung", "東涌", tcl);

    createStation("WKS", "Wu Kai Sha", "烏溪沙", tml);
    createStation("MOS", "Ma On Shan", "馬鞍山", tml);
    createStation("HEO", "Heng On", "恒安", tml);
    createStation("TSH", "Tai Shui Hang", "大水坑", tml);
    createStation("SHM", "Shek Mun", "石門", tml);
    createStation("CIO", "City One", "第一城", tml);
    createStation("STW", "Sha Tin Wai", "沙田圍", tml);
    createStation("CKT", "Che Kung Temple", "車公廟", tml);
    createStation("TAW", "Tai Wai", "大圍", tml, eal);
    createStation("HIK", "Hin Keng", "顯徑", tml);
    createStation("DIH", "Diamond Hill", "鑽石山", tml, ktl);
    createStation("KAT", "Kai Tak", "啟德", tml);
    createStation("SUW", "Sung Wong Toi", "宋皇臺", tml);
    createStation("TKW", "To Kwa Wan", "土瓜灣", tml);
    createStation("HOM", "Ho Man Tin", "何文田", tml, ktl);
    createStation("HUH", "Hung Hom", "紅磡", tml, eal);
    createStation("ETS", "East Tsim Sha Tsui", "尖東", tml);
    createStation("AUS", "Austin", "柯士甸", tml);
    createStation("MEF", "Mei Foo", "美孚", tml, twl);
    createStation("TWW", "Tsuen Wan West", "荃灣西", tml);
    createStation("KSR", "Kam Sheung Road", "錦上路", tml);
    createStation("YUL", "Yuen Long", "元朗", tml);
    createStation("LOP", "Long Ping", "朗屏", tml);
    createStation("TIS", "Tin Shui Wai", "天水圍", tml);
    createStation("SIH", "Siu Hong", "兆康", tml);
    createStation("TUM", "Tuen Mun", "屯門", tml);

    createStation("NOP", "North Point", "北角", tkl, isl);
    createStation("QUB", "Quarry Bay", "鰂魚涌", tkl, isl);
    createStation("YAT", "Yau Tong", "油塘", tkl, ktl);
    createStation("TIK", "Tiu Keng Leng", "調景嶺", tkl, ktl);
    createStation("TKO", "Tseung Kwan O", "將軍澳", tkl);
    createStation("LHP", "LOHAS Park", "康城", tkl);
    createStation("HAH", "Hang Hau", "坑口", tkl);
    createStation("POA", "Po Lam", "寶琳", tkl);

    createStation("ADM", "Admiralty", "金鐘", eal, sil, twl, isl);
    createStation("EXC", "Exhibition Centre", "會展", eal);
    createStation("MKK", "Mong Kok East", "旺角東", eal);
    createStation("KOT", "Kowloon Tong", "九龍塘", eal, ktl);
    createStation("SHT", "Sha Tin", "沙田", eal);
    createStation("FOT", "Fo Tan", "火炭", eal);
    createStation("RAC", "Racecourse", "馬場", eal);
    createStation("UNI", "University", "大學", eal);
    createStation("TAP", "Tai Po Market", "大埔墟", eal);
    createStation("TWO", "Tai Wo", "太和", eal);
    createStation("FAN", "Fanling", "粉嶺", eal);
    createStation("SHS", "Sheung Shui", "上水", eal);
    createStation("LOW", "Lo Wu", "羅湖", eal);
    createStation("LMC", "Lok Ma Chau", "落馬洲", eal);

    createStation("OCP", "Ocean Park", "海洋公園", sil);
    createStation("WCH", "Wong Chuk Hang", "黃竹坑", sil);
    createStation("LET", "Lei Tung", "利東", sil);
    createStation("SOH", "South Horizons", "海怡半島", sil);

    createStation("CEN", "Central", "中環", twl, isl);
    createStation("TST", "Tsim Sha Tsui", "尖沙咀", twl);
    createStation("JOR", "Jordan", "佐敦", twl);
    createStation("YMT", "Yau Ma Tei", "油麻地", twl, ktl);
    createStation("MOK", "Mong Kok", "旺角", twl, ktl);
    createStation("PRE", "Prince Edward", "太子", twl, ktl);
    createStation("SSP", "Sham Shui Po", "深水埗", twl);
    createStation("CSW", "Cheung Sha Wan", "長沙灣", twl);
    createStation("LCK", "Lai Chi Kok", "荔枝角", twl);
    createStation("KWF", "Kwai Fong", "葵芳", twl);
    createStation("KWH", "Kwai Hing", "葵興", twl);
    createStation("TWH", "Tai Wo Hau", "大窩口", twl);
    createStation("TSW", "Tsuen Wan", "荃灣", twl);

    createStation("KET", "Kennedy Town", "堅尼地城", isl);
    createStation("HKU", "HKU", "香港大學", isl);
    createStation("SYP", "Sai Ying Pun", "西營盤", isl);
    createStation("SHW", "Sheung Wan", "上環", isl);
    createStation("WAC", "Wan Chai", "灣仔", isl);
    createStation("CAB", "Causeway Bay", "銅鑼灣", isl);
    createStation("TIH", "Tin Hau", "天后", isl);
    createStation("FOH", "Fortress Hill", "炮台山", isl);
    createStation("TAK", "Tai Koo", "太古", isl);
    createStation("SWH", "Sai Wan Ho", "西灣河", isl);
    createStation("SKW", "Shau Kei Wan", "筲箕灣", isl);
    createStation("HFC", "Heng Fa Chuen", "杏花邨", isl);
    createStation("CHW", "Chai Wan", "柴灣", isl);

    createStation("WHA", "Whampoa", "黃埔", ktl);
    createStation("SKM", "Shek Kip Mei", "石硤尾", ktl);
    createStation("LOF", "Lok Fu", "樂富", ktl);
    createStation("WTS", "Wong Tai Sin", "黃大仙", ktl);
    createStation("CHH", "Choi Hung", "彩虹", ktl);
    createStation("KOB", "Kowloon Bay", "九龍灣", ktl);
    createStation("NTK", "Ngau Tau Kok", "牛頭角", ktl);
    createStation("KWT", "Kwun Tong", "觀塘", ktl);
    createStation("LAT", "Lam Tin", "藍田", ktl);

    createStation("DIS", "Disneyland Resort", "迪士尼", drl);
  }

}
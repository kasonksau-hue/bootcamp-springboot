package com.bootcamp.demo.bc_mtr_station.model.signal;

public enum LineSignal {
  GREEN,
  YELLOW,
  RED,

  public static Singal get(in delayCount){
    if (delayCount == 0)
      return GREEN; 
    else if(delayCount ==1)
      return YELLOW; 
    else
      return RED; 
  }

}

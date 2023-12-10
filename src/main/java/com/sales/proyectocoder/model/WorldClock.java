package com.sales.proyectocoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldClock {
  private String $id;
  private String currentDateTime;
  private String utcOffset;
  private Boolean isDayLightSavingsTime;
  private String dayOfTheWeek;
  private String timeZoneName;
  private Long currentFileTime;
  private String ordinalDate;
  private String serviceResponse;
}

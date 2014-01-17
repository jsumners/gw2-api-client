package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class Sector {
  @JsonProperty("sector_id")
  private int sectorId;
  private String name;
  private String level;
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public Sector() {}

  public int getSectorId() {
    return this.sectorId;
  }

  public void setSectorId(int sectorId) {
    this.sectorId = sectorId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public MapCoordinate getCoordinate() {
    return this.coordinate;
  }

  public void setCoordinate(MapCoordinate coordinate) {
    this.coordinate = coordinate;
  }
}
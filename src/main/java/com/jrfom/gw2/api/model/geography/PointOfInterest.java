package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.geography.MapCoordinate;

@Gw2ApiVersion("v1")
public class PointOfInterest {
  @JsonProperty("poi_id")
  private int poiId;
  private String name;
  private String type;
  private int floor;
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public PointOfInterest() {}

  public int getPoiId() {
    return this.poiId;
  }

  public void setPoiId(int poiId) {
    this.poiId = poiId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getFloor() {
    return this.floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public MapCoordinate getCoordinate() {
    return this.coordinate;
  }

  public void setCoordinate(MapCoordinate coordinate) {
    this.coordinate = coordinate;
  }
}
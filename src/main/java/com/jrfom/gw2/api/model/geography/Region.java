package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class Region {
  private String name;
  @JsonProperty("label_coord")
  private MapCoordinate labelCoordinate;
  private FloorMapList maps;

  public Region() {}

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MapCoordinate getLabelCoordinate() {
    return this.labelCoordinate;
  }

  public void setLabelCoordinate(MapCoordinate labelCoordinate) {
    this.labelCoordinate = labelCoordinate;
  }

  public FloorMapList getMaps() {
    return this.maps;
  }

  public void setMaps(FloorMapList maps) {
    this.maps = maps;
  }
}
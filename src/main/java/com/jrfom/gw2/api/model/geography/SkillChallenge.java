package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.geography.MapCoordinate;

@Gw2ApiVersion("v1")
public class SkillChallenge {
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public SkillChallenge() {}

  public MapCoordinate getCoordinate() {
    return this.coordinate;
  }

  public void setCoordinate(MapCoordinate coordinate) {
    this.coordinate = coordinate;
  }
}
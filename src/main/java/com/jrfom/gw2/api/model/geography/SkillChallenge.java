package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the location of a skill point challenge for use by
 * {@link com.jrfom.gw2.api.model.geography.FloorMap}.
 */
@Gw2ApiVersion("v1")
public class SkillChallenge {
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public SkillChallenge() {}

  /**
   * The in-game map location of the skill challenge.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}.
   */
  public MapCoordinate getCoordinate() {
    return this.coordinate;
  }

  public void setCoordinate(MapCoordinate coordinate) {
    this.coordinate = coordinate;
  }
}
package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a sub-region of a
 * {@link com.jrfom.gw2.api.model.geography.FloorMap}.
 */
@Gw2ApiVersion("v1")
public class Sector {
  @JsonProperty("sector_id")
  private int sectorId;
  private String name;
  private String level;
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public Sector() {}

  /**
   * The unique identifier for the sector.
   *
   * @return An integer identifier.
   */
  public int getSectorId() {
    return this.sectorId;
  }

  public void setSectorId(int sectorId) {
    this.sectorId = sectorId;
  }

  /**
   * The in-game name of the sector.
   *
   * @return A string name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // TODO: make this not a string
  /**
   * The in-game character level for this sector.
   *
   * @return A string integer representing the character level for this region.
   */
  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  /**
   * A coordinate that designates where this sector is located. This is
   * usually the center of the sector.
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
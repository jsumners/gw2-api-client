package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a game region as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/map_floor">/v1/map_floor</a>.
 */
@Gw2ApiVersion("v1")
public class Region {
  private String name;
  @JsonProperty("label_coord")
  private MapCoordinate labelCoordinate;
  private FloorMapList maps;

  public Region() {}

  /**
   * The in-game name of the region. For example, "Kryta".
   *
   * @return A string name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The coordinates of the region label on the in-game world map.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}.
   */
  public MapCoordinate getLabelCoordinate() {
    return this.labelCoordinate;
  }

  public void setLabelCoordinate(MapCoordinate labelCoordinate) {
    this.labelCoordinate = labelCoordinate;
  }

  /**
   * Retrieve a list of {@link com.jrfom.gw2.api.model.geography.FloorMap}s
   * that are contained within the region.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.FloorMapList}.
   */
  public FloorMapList getMaps() {
    return this.maps;
  }

  public void setMaps(FloorMapList maps) {
    this.maps = maps;
  }
}
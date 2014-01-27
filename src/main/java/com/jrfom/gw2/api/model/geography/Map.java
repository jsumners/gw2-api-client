package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the details of a game map as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/maps">/v1/maps</a>.
 */
@Gw2ApiVersion("v1")
public class Map {
  @JsonIgnore
  private int mapId;
  @JsonProperty("map_name")
  private String mapName;
  @JsonProperty("min_level")
  private int minLevel;
  @JsonProperty("max_level")
  private int maxLevel;
  @JsonProperty("default_floor")
  private int defaultFloor;
  private ArrayList<Integer> floors;
  @JsonProperty("region_id")
  private int regionId;
  @JsonProperty("region_name")
  private String regionName;
  @JsonProperty("continent_id")
  private int continentId;
  @JsonProperty("continent_name")
  private String continentName;
  @JsonProperty("map_rect")
  private MapRectangle mapRect;
  @JsonProperty("continent_rect")
  private MapRectangle continentRect;

  Map() {}

  /**
   * Retrieve the numeric identifier for the map.
   *
   * @return An integer that uniquely identifies the map.
   */
  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  /**
   * The in-game name of the map. For example, "Queensdale".
   *
   * @return The string name of the map.
   */
  public String getMapName() {
    return this.mapName;
  }

  public void setMapName(String mapName) {
    this.mapName = mapName;
  }

  /**
   * The minimum target character level for the map.
   *
   * @return An integer character level.
   */
  public int getMinLevel() {
    return this.minLevel;
  }

  public void setMinLevel(int minLevel) {
    this.minLevel = minLevel;
  }

  /**
   * The maximum target character level for the map.
   *
   * @return An integer character level.
   */
  public int getMaxLevel() {
    return this.maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  /**
   * The default {@link com.jrfom.gw2.api.model.geography.Floor} for this map.
   *
   * @return An integer that identifies the default floor.
   */
  public int getDefaultFloor() {
    return this.defaultFloor;
  }

  public void setDefaultFloor(int defaultFloor) {
    this.defaultFloor = defaultFloor;
  }

  /**
   * A list of identifiers that detail the
   * {@link com.jrfom.gw2.api.model.geography.Floor}s available on the map.
   *
   * @return A list of floor ids.
   */
  public ArrayList<Integer> getFloors() {
    return this.floors;
  }

  public void setFloors(ArrayList<Integer> floors) {
    this.floors = floors;
  }

  /**
   * An identifier for the {@link com.jrfom.gw2.api.model.geography.Region} in
   * which the map is located.
   *
   * @return An integer region id.
   */
  public int getRegionId() {
    return this.regionId;
  }

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }

  /**
   * The in-game name of the {@link com.jrfom.gw2.api.model.geography.Region} in
   * which the map is location. For exmaple, "Kryta".
   *
   * @return The name of the map's parent region.
   */
  public String getRegionName() {
    return this.regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  /**
   * An identifier for the {@link com.jrfom.gw2.api.model.geography.Continent}
   * which the map is a part of.
   *
   * @return An integer continent id.
   */
  public int getContinentId() {
    return this.continentId;
  }

  public void setContinentId(int continentId) {
    this.continentId = continentId;
  }

  /**
   * The in-game name of the {@link com.jrfom.gw2.api.model.geography.Continent}
   * which the map is a part of. For example, "Tyria".
   *
   * @return The name of the map's parent continent.
   */
  public String getContinentName() {
    return this.continentName;
  }

  public void setContinentName(String continentName) {
    this.continentName = continentName;
  }

  /**
   * The dimensions of the map.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.
   */
  public MapRectangle getMapRect() {
    return this.mapRect;
  }

  public void setMapRect(MapRectangle mapRect) {
    this.mapRect = mapRect;
  }

  /**
   * The dimensions of the map according to the game coordinate system.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.
   */
  public MapRectangle getContinentRect() {
    return this.continentRect;
  }

  public void setContinentRect(MapRectangle continentRect) {
    this.continentRect = continentRect;
  }
}
package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

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

  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public String getMapName() {
    return this.mapName;
  }

  public void setMapName(String mapName) {
    this.mapName = mapName;
  }

  public int getMinLevel() {
    return this.minLevel;
  }

  public void setMinLevel(int minLevel) {
    this.minLevel = minLevel;
  }

  public int getMaxLevel() {
    return this.maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  public int getDefaultFloor() {
    return this.defaultFloor;
  }

  public void setDefaultFloor(int defaultFloor) {
    this.defaultFloor = defaultFloor;
  }

  public ArrayList<Integer> getFloors() {
    return this.floors;
  }

  public void setFloors(ArrayList<Integer> floors) {
    this.floors = floors;
  }

  public int getRegionId() {
    return this.regionId;
  }

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }

  public String getRegionName() {
    return this.regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public int getContinentId() {
    return this.continentId;
  }

  public void setContinentId(int continentId) {
    this.continentId = continentId;
  }

  public String getContinentName() {
    return this.continentName;
  }

  public void setContinentName(String continentName) {
    this.continentName = continentName;
  }

  public MapRectangle getMapRect() {
    return this.mapRect;
  }

  public void setMapRect(MapRectangle mapRect) {
    this.mapRect = mapRect;
  }

  public MapRectangle getContinentRect() {
    return this.continentRect;
  }

  public void setContinentRect(MapRectangle continentRect) {
    this.continentRect = continentRect;
  }
}
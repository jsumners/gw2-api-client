package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class Event {
  private String name;
  private int level;
  @JsonProperty("map_id")
  private int mapId;
  private ArrayList<String> flags;
  private Location location;

  public Event() {}

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public ArrayList<String> getFlags() {
    return flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  public Location getLocation() {
    return this.location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
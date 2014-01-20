package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class WorldEventStatus {
  @JsonIgnore
  public static final String STATUS_ACTIVE = "Active";
  @JsonIgnore
  public static final String STATUS_FAIL = "Fail";
  @JsonIgnore
  public static final String STATUS_INACTIVE = "Inactive";
  @JsonIgnore
  public static final String STATUS_PREPARATION = "Preparation";
  @JsonIgnore
  public static final String STATUS_SUCCESS = "Success";
  @JsonIgnore
  public static final String STATUS_WARMUP = "Warmup";

  @JsonProperty("world_id")
  private int worldId;
  @JsonProperty("map_id")
  private int mapId;
  @JsonProperty("event_id")
  private String eventId;
  private String state;

  public WorldEventStatus() {}

  public int getWorldId() {
    return this.worldId;
  }

  public void setWorldId(int worldId) {
    this.worldId = worldId;
  }

  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public String getEventId() {
    return this.eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
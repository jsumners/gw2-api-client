package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an {@link com.jrfom.gw2.api.model.events.Event} status as
 * returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/events">/v1/events</a>.
 */
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

  /**
   * An identifier for the game world where the event takes place. Every event
   * can happen in any game world. This just ties the current status for that
   * event to a {@link com.jrfom.gw2.api.model.WorldName}.
   *
   * @return The world identifier.
   */
  public int getWorldId() {
    return this.worldId;
  }

  public void setWorldId(int worldId) {
    this.worldId = worldId;
  }

  /**
   * The identifier for the {@link com.jrfom.gw2.api.model.geography.Map}
   * where the event is located.
   *
   * @return A map identifier.
   */
  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  /**
   * The UUID that represents the event throughout the rest of the API.
   *
   * @return A string representation of the UUID.
   */
  public String getEventId() {
    return this.eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  /**
   * <p>The current status of the event. The possible states are:</p>
   *
   * <ul>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_ACTIVE}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_FAIL}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_INACTIVE}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_PREPARATION}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_SUCCESS}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_WARMUP}</li>
   * </ul>
   *
   * @return A string representing the current event status.
   */
  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
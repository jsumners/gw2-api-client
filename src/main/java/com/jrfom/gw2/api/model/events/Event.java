package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an event object as returned by the
 * <a href="http://wiki.guildwars2.com/wiki/API:1/event_details">/v1/event_details</a>
 * API method.
 */
@Gw2ApiVersion("v1")
public class Event {
  private String name;
  private int level;
  @JsonProperty("map_id")
  private int mapId;
  private ArrayList<String> flags;
  private Location location;

  public Event() {}

  /**
   * The name of the event.
   *
   * @return The name of the event as returned by the remote API.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The in-game character level of the event.
   *
   * @return The level as returned by the remote API.
   */
  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * The map id for the map where the event takes place. Map ids can be looked
   * up via
   * <a href="http://wiki.guildwars2.com/wiki/API:1/map_names">/v1/map_names</a>.
   *
   * @return The identifier as returned by the remote API.
   */
  public int getMapId() {
    return this.mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  /**
   * <p>A list of flags, instances of {@link java.lang.String}, that further
   * describe the event. The possible flags are:</p>
   *
   * <ul>
   *   <li>{@code group_event}</li>
   *   <li>{@code map_wide}</li>
   * </ul>
   *
   * <p>See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/event_details">API:1/event_details</a>
   * for more information.
   * </p>
   *
   * @return The flags as returned by the remote API. Can be an empty array.
   */
  public ArrayList<String> getFlags() {
    return flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  /**
   * A {@link com.jrfom.gw2.api.model.events.Location} object that describes
   * the location of the event.
   *
   * @return An instance of {@linkplain com.jrfom.gw2.api.model.events.Location}.
   */
  public Location getLocation() {
    return this.location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
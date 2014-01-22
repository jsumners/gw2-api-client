package com.jrfom.gw2.api.model.events;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an event name as returned by the
 * <a href="http://wiki.guildwars2.com/wiki/API:1/event_names">/v1/event_names</a>
 * API method.
 */
@Gw2ApiVersion("v1")
public class EventName {
  private String id;
  private String name;

  public EventName() {}

  /**
   * The unique id that represents this {@link com.jrfom.gw2.api.model.events.Event}
   * throughout the rest of the API.
   *
   * @return A string representation of a UUID.
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * The in-game name of the event as it would show up in a player's quest
   * log.
   *
   * @return A string representation of the name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
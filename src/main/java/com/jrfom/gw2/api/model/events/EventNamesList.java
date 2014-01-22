package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the list of {@link com.jrfom.gw2.api.model.events.EventName}s as
 * returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/event_names">/v1/event_names</a>.
 */
@Gw2ApiVersion("v1")
public class EventNamesList extends ArrayList<EventName> {
  public EventNamesList() {}

  /**
   * A helper method for retrieving a specific event from the list.
   *
   * @param id The UUID that identifies the event.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventName}.
   */
  public Optional<EventName> getEventWithId(String id) {
    Optional<EventName> result = Optional.absent();

    for (EventName eventName : this) {
      if (eventName.getId().equals(id)) {
        result = Optional.of(eventName);
        break;
      }
    }

    return result;
  }

  /**
   * A helper method for retrieving a specific event from the list.
   *
   * @param name The in-game name of the event, case sensitive.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventName}.
   */
  public Optional<EventName> getEventWithName(String name) {
    Optional<EventName> result = Optional.absent();

    for (EventName eventName : this) {
      if (eventName.getName().equals(name)) {
        result = Optional.of(eventName);
        break;
      }
    }

    return result;
  }
}
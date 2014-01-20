package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class EventNamesList extends ArrayList<EventName> {
  public EventNamesList() {}

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
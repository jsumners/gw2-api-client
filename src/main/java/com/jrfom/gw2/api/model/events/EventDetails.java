package com.jrfom.gw2.api.model.events;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.EventDetailsDeserializer;

/**
 * Provides a model for {@code /v1/event_details.json}. The result is a
 * {@link java.util.HashMap} with keys set to event identifiers and values
 * set to instances of {@link com.jrfom.gw2.api.model.events.Event}. If there
 * were no results, the size of the map will be zero.
 */
@JsonDeserialize(using = EventDetailsDeserializer.class)
@Gw2ApiVersion("v1")
public class EventDetails extends HashMap<String, Event> {
  public EventDetails() {
    super(0);
  }
}
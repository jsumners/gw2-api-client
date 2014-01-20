package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.events.WorldEventStatus;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;

public class WorldEventStatusListSerializer extends JsonSerializer<WorldEventsStatusList> {
  @Override
  public void serialize(WorldEventsStatusList worldEventStatuses, JsonGenerator jgen, SerializerProvider serializerProvider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartObject();

    jgen.writeArrayFieldStart("events");
    for (WorldEventStatus eventStatus : worldEventStatuses) {
      jgen.writeObject(eventStatus);
    }
    jgen.writeEndArray();

    jgen.writeEndObject();
  }
}
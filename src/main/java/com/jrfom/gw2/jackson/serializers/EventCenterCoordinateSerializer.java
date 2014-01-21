package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.events.EventCenterCoordinate;

public class EventCenterCoordinateSerializer extends JsonSerializer<EventCenterCoordinate> {
  @Override
  public void serialize(EventCenterCoordinate eventCenterCoordinate, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartArray();
    jgen.writeNumber(eventCenterCoordinate.getX());
    jgen.writeNumber(eventCenterCoordinate.getY());
    jgen.writeNumber(eventCenterCoordinate.getZ());
    jgen.writeEndArray();
  }
}
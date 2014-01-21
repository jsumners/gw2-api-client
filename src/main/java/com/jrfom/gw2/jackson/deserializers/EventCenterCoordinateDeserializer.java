package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.events.EventCenterCoordinate;

public class EventCenterCoordinateDeserializer extends JsonDeserializer<EventCenterCoordinate> {
  @Override
  public EventCenterCoordinate deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    EventCenterCoordinate coordinate = new EventCenterCoordinate();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);

    coordinate.setX(node.get(0).asDouble());
    coordinate.setY(node.get(1).asDouble());
    coordinate.setZ(node.get(2).asDouble());

    return coordinate;
  }
}
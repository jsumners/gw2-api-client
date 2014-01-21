package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.events.*;

public class LocationDeserializer extends JsonDeserializer<Location> {
  private HashMap<String, Class<? extends EventLocation>> eventLocationTypes;

  public LocationDeserializer() {
    this.eventLocationTypes = new HashMap<>(3);
    this.eventLocationTypes.put("cylinder", EventCylinderLocation.class);
    this.eventLocationTypes.put("poly", EventPolyLocation.class);
    this.eventLocationTypes.put("sphere", EventSphereLocation.class);
  }

  @Override
  public Location deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    Location location;
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    String locationType = node.get("type").textValue().toLowerCase();

    location = codec.treeToValue(node, this.eventLocationTypes.get(locationType));

    return location;
  }
}
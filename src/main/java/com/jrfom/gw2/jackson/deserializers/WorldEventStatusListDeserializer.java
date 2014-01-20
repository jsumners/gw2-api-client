package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.events.WorldEventStatus;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;

@Gw2ApiVersion("v1")
public class WorldEventStatusListDeserializer extends JsonDeserializer<WorldEventsStatusList> {
  @Override
  public WorldEventsStatusList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    WorldEventsStatusList result = new WorldEventsStatusList();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("events");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<JsonNode> iterator = node.elements();
    while (iterator.hasNext()) {
      JsonNode jsonEvent = iterator.next();
      WorldEventStatus eventStatus = mapper.readValue(
        jsonEvent.toString(),
        WorldEventStatus.class
      );
      result.add(eventStatus);
    }

    return result;
  }
}
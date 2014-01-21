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
import com.jrfom.gw2.api.model.events.Event;
import com.jrfom.gw2.api.model.events.EventDetails;

@Gw2ApiVersion("v1")
public class EventDetailsDeserializer extends JsonDeserializer<EventDetails> {

  @Override
  public EventDetails deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    EventDetails details = new EventDetails();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("events");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<String> iterator = node.fieldNames();
    while (iterator.hasNext()) {
      String eventId = iterator.next();
      JsonNode eventNode = node.get(eventId);
      Event event = mapper.readValue(
        eventNode.toString(),
        Event.class
      );
      details.put(eventId, event);
    }

    return details;
  }
}
package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.events.Range;

public class RangeDeserializer extends JsonDeserializer<Range> {
  @Override
  public Range deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    Range range = new Range();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);

    range.setX(node.get(0).asDouble());
    range.setY(node.get(1).asDouble());

    return range;
  }
}
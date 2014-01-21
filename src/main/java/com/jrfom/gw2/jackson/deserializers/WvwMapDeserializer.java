package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.wvw.*;

public class WvwMapDeserializer extends JsonDeserializer<WvwMap> {
  private HashMap<String, Class<? extends RedHomeMap>> classRegistry;

  public WvwMapDeserializer() {
    this.classRegistry = new HashMap<>(4);
    this.classRegistry.put("redhome", RedHomeMap.class);
    this.classRegistry.put("bluehome", BlueHomeMap.class);
    this.classRegistry.put("greenhome", GreenHomeMap.class);
    this.classRegistry.put("center", EternalBattleGroundsMap.class);
  }

  @Override
  public WvwMap deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    WvwMap map;

    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    String mapType = node.get("type").asText().toLowerCase();

    map = codec.treeToValue(node, this.classRegistry.get(mapType));

    return map;
  }
}
package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.wvw.BloodlustBonus;
import com.jrfom.gw2.api.model.wvw.Bonus;

public class WvwBonusDeserializer extends JsonDeserializer<Bonus> {
  private HashMap<String, Class<? extends BloodlustBonus>> classRegistry;

  public WvwBonusDeserializer() {
    this.classRegistry = new HashMap<>(1);
    this.classRegistry.put("bloodlust", BloodlustBonus.class);
  }

  @Override
  public Bonus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    Bonus bonus;
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    String bonusType = node.get("type").asText().toLowerCase();

    bonus = codec.treeToValue(node, this.classRegistry.get(bonusType));

    return bonus;
  }
}
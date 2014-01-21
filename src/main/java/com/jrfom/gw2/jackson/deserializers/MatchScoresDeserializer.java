package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.wvw.MatchScores;

public class MatchScoresDeserializer extends JsonDeserializer<MatchScores> {
  @Override
  public MatchScores deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    MatchScores scores = new MatchScores();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);

    scores.setRedScore(node.get(0).asInt());
    scores.setBlueScore(node.get(1).asInt());
    scores.setGreenScore(node.get(2).asInt());

    return scores;
  }
}
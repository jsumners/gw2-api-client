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
import com.jrfom.gw2.api.model.wvw.MatchInfo;
import com.jrfom.gw2.api.model.wvw.MatchesList;

public class MatchesListDeserializer extends JsonDeserializer<MatchesList> {
  @Override
  public MatchesList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    MatchesList list = new MatchesList();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("wvw_matches");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<JsonNode> iterator = node.elements();
    while (iterator.hasNext()) {
      MatchInfo info = mapper.readValue(
        iterator.next().toString(),
        MatchInfo.class
      );
      list.add(info);
    }

    return list;
  }
}
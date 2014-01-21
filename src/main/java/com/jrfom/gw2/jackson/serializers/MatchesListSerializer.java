package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.wvw.MatchInfo;
import com.jrfom.gw2.api.model.wvw.MatchesList;

public class MatchesListSerializer extends JsonSerializer<MatchesList> {
  @Override
  public void serialize(MatchesList value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartObject();

    jgen.writeArrayFieldStart("wvw_matches");
    for (MatchInfo info : value) {
      jgen.writeObject(info);
    }
    jgen.writeEndArray();

    jgen.writeEndObject();
  }
}
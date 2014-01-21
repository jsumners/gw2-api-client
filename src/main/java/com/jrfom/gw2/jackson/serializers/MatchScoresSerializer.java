package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.wvw.MatchScores;

public class MatchScoresSerializer extends JsonSerializer<MatchScores> {
  @Override
  public void serialize(MatchScores value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartArray();
    jgen.writeNumber(value.getRedScore());
    jgen.writeNumber(value.getBlueScore());
    jgen.writeNumber(value.getGreenScore());
    jgen.writeEndArray();
  }
}
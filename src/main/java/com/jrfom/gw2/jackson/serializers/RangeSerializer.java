package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.events.Range;

public class RangeSerializer extends JsonSerializer<Range> {
  @Override
  public void serialize(Range range, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartArray();
    jgen.writeNumber(range.getX());
    jgen.writeNumber(range.getY());
    jgen.writeEndArray();
  }
}
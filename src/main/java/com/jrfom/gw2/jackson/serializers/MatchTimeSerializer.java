package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class MatchTimeSerializer extends JsonSerializer<OffsetDateTime> {
  @Override
  public void serialize(OffsetDateTime value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    jgen.writeString(value.toString(formatter));
  }
}
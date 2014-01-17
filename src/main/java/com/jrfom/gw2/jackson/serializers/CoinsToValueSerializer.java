package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.Coins;

public class CoinsToValueSerializer extends JsonSerializer<Coins> {
  @Override
  public void serialize(Coins value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    //jgen.writeString(String.format("%1.0f", value.getValue()));
    Number outValue = value.getValue();
    jgen.writeNumber(outValue.intValue());
  }
}
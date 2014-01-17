package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.items.Item;

@Gw2ApiVersion("v1")
public class ItemSerializer extends JsonSerializer<Item> {
  @Override
  public void serialize(Item value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
  }
}
package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.items.GenericItem;

@Gw2ApiVersion("v1")
public class ItemSerializer extends JsonSerializer<GenericItem> {
  @Override
  public void serialize(GenericItem value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
  }
}
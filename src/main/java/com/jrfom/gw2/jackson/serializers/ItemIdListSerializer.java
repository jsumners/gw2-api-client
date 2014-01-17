package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.items.ItemIdList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Gw2ApiVersion("v1")
public class ItemIdListSerializer extends JsonSerializer<ItemIdList> {
  private static final Logger log = LoggerFactory.getLogger(ItemIdListSerializer.class);

  @Override
  public void serialize(ItemIdList value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    log.debug("Serializing ItemIdList with custom Jackson serializer");
    jgen.writeStartObject();

    jgen.writeArrayFieldStart("items");
    for (Number itemId : value) {
      jgen.writeNumber(itemId.longValue());
    }
    jgen.writeEndArray();

    jgen.writeEndObject();
  }
}
package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapsList;

@Gw2ApiVersion("v1")
public class MapsListSerializer extends JsonSerializer<MapsList> {
  @Override
  public void serialize(MapsList value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartObject();

    jgen.writeObjectFieldStart("maps");
    for (Map map : value) {
      jgen.writeObjectField("" + map.getMapId(), map);
    }
    jgen.writeEndObject();

    jgen.writeEndObject();
  }
}
package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.colors.Color;
import com.jrfom.gw2.api.model.colors.ColorsList;

@Gw2ApiVersion("v1")
public class ColorsListSerializer extends JsonSerializer<ColorsList> {
  @Override
  public void serialize(ColorsList value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartObject();

    jgen.writeObjectFieldStart("colors");
    for (Color color : value) {
      jgen.writeObjectField("" + color.getColorId(), color);
    }
    jgen.writeEndObject();

    jgen.writeEndObject();
  }
}
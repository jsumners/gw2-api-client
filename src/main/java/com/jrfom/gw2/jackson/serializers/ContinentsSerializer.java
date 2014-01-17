package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.geography.Continents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Gw2ApiVersion
public class ContinentsSerializer extends JsonSerializer<Continents> {
  private static final Logger log = LoggerFactory.getLogger(ContinentsSerializer.class);

  @Override
  public void serialize(Continents value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    log.debug("Serializing Continents with custom Jackson serializer");
    jgen.writeStartObject();

    jgen.writeObjectFieldStart("continents");
    for (String key : value.keySet()) {
      jgen.writeObjectField(key, value.get(key));
    }
    jgen.writeEndObject();

    jgen.writeEndObject();
  }
}
package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.colors.Color;
import com.jrfom.gw2.api.model.colors.ColorsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Gw2ApiVersion("v1")
public class ColorsListDeserializer extends JsonDeserializer<ColorsList> {
  private static final Logger log = LoggerFactory.getLogger(ColorsListDeserializer.class);

  @Override
  public ColorsList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    log.debug("Deserializing ColorsList with custom Jackson deserializer");
    ColorsList result = new ColorsList(0);
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("colors");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<String> fieldNames = node.fieldNames();
    while(fieldNames.hasNext()) {
      String keyName = fieldNames.next();
      JsonNode colorNode = node.get(keyName);
      Color color = mapper.readValue(colorNode.toString(), Color.class);
      color.setColorId(Integer.parseInt(keyName));
      result.add(color);
    }

    return result;
  }
}
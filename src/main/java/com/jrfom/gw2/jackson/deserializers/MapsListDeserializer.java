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
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Gw2ApiVersion("v1")
public class MapsListDeserializer extends JsonDeserializer<MapsList> {
  private static final Logger log = LoggerFactory.getLogger(MapsListDeserializer.class);

  @Override
  public MapsList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    log.debug("Deserializing MapsList with custom Jackson deserializer");
    MapsList mapsList = new MapsList(0);
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("maps");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<String> iterator = node.fieldNames();
    while (iterator.hasNext()) {
      String keyName = iterator.next();
      JsonNode mapNode = node.get(keyName);
      Map map = mapper.readValue(mapNode.toString(), Map.class);
      map.setMapId(Integer.parseInt(keyName));
      mapsList.add(map);
    }

    return mapsList;
  }
}
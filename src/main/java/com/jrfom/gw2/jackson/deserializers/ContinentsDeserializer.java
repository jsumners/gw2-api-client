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
import com.jrfom.gw2.api.model.geography.Continent;
import com.jrfom.gw2.api.model.geography.Continents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Gw2ApiVersion("v1")
public class ContinentsDeserializer extends JsonDeserializer<Continents> {
  private static final Logger log = LoggerFactory.getLogger(ContinentsDeserializer.class);

  @Override
  public Continents deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    log.info("Deserializing Continents with custom Jackson deserializer");
    Continents continents = new Continents();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("continents");

    ObjectMapper mapper = new ObjectMapper();
    Iterator<String> iterator = node.fieldNames();
    while (iterator.hasNext()) {
      String keyName = iterator.next();
      Continent continent = mapper.readValue(
        node.get(keyName).toString(),
        Continent.class
      );
      continents.put(keyName, continent);
    }

    return continents;
  }
}
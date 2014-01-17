package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapsList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MapsListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(MapsListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running MapsList custom serializer test");
    ObjectMapper mapper = new ObjectMapper();
    MapsList mapsList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/MapsList.json"),
      MapsList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(mapsList);

    MapsList mapsList2 = mapper.readValue(json, MapsList.class);
    assertTrue(mapsList.size() == mapsList2.size());

    Optional<Map> result = mapsList.getMapWithName("The Perils of Friendship");
    assertTrue(result.isPresent());
    Map map = result.get();
    result = mapsList2.getMapWithName("The Perils of Friendship");
    assertTrue(result.isPresent());
    Map map2 = result.get();
    assertTrue(map.getMapName().equals(map2.getMapName()));
    assertTrue(map.getMapId() == map2.getMapId());
  }
}
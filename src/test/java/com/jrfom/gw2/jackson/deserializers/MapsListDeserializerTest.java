package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapsList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MapsListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(MapsListDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running test for MapsList custom deserializer");
    MapsListDeserializer deserializer = new MapsListDeserializer();
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule(
      "my_module",
      new Version(1, 0, 0, null, "com.jrfom", "foo")
    );
    simpleModule.addDeserializer(MapsList.class, deserializer);
    mapper.registerModule(simpleModule);

    MapsList mapsList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/MapsList.json"),
      MapsList.class
    );

    assertTrue(mapsList.size() > 0);

    Optional<Map> result = mapsList.getMapWithName("The Perils of Friendship");
    assertTrue(result.isPresent());

    Map map = result.get();
    assertTrue(map.getMapId() == 110);
    assertTrue(map.getMapName().equals("The Perils of Friendship"));
    assertTrue(map.getMinLevel() == 3);
    assertTrue(map.getMaxLevel() == 3);
    assertTrue(map.getDefaultFloor() == 1);
    assertTrue(map.getFloors().size() == 2);
    assertTrue(map.getFloors().get(0) == 1);
    assertTrue(map.getFloors().get(1) == 2);
    assertTrue(map.getRegionId() == 8);
    assertTrue(map.getRegionName().equals("Steamspur Mountains"));
    assertTrue(map.getContinentId() == 1);
    assertTrue(map.getContinentName().equals("Tyria"));
    assertTrue(map.getMapRect().size() == 2);
    assertTrue(map.getMapRect().getUpperLeft().getX().intValue() == -21504);
    assertTrue(map.getMapRect().getUpperLeft().getY().intValue() == -21504);
    assertTrue(map.getMapRect().getLowerRight().getX().intValue() == 24576);
    assertTrue(map.getMapRect().getLowerRight().getY().intValue() == 21504);
    assertTrue(map.getContinentRect().size() == 2);
    assertTrue(map.getContinentRect().getUpperLeft().getX().intValue() == 10240);
    assertTrue(map.getContinentRect().getUpperLeft().getY().intValue() == 9856);
    assertTrue(map.getContinentRect().getLowerRight().getX().intValue() == 12160);
    assertTrue(map.getContinentRect().getLowerRight().getY().intValue() == 11648);
  }
}
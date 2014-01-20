package com.jrfom.gw2.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WorldNamesListTest {
  private static final Logger log = LoggerFactory.getLogger(WorldNamesListTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running WorldNamesList deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    WorldNamesList worldNames = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldNames.json"),
      WorldNamesList.class
    );

    assertTrue(worldNames.size() == 5);
    assertTrue(worldNames.get(0).getId() == 2010);
    assertTrue(worldNames.get(0).getName().equals("Seafarer's Rest"));
    assertTrue(worldNames.get(1).getId() == 2104);
    assertTrue(worldNames.get(1).getName().equals("Vizunah Square [FR]"));
    assertTrue(worldNames.get(2).getId() == 1011);
    assertTrue(worldNames.get(2).getName().equals("Stormbluff Isle"));
    assertTrue(worldNames.get(3).getId() == 2203);
    assertTrue(worldNames.get(3).getName().equals("Elona Reach [DE]"));
    assertTrue(worldNames.get(4).getId() == 1009);
    assertTrue(worldNames.get(4).getName().equals("Fort Aspenwood"));
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running WorldNamesList serialization test");
    ObjectMapper mapper = new ObjectMapper();
    WorldNamesList worldNames = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldNames.json"),
      WorldNamesList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(worldNames);

    WorldNamesList worldNames2 = mapper.readValue(json, WorldNamesList.class);
    assertTrue(worldNames.size() == worldNames2.size());
    assertTrue(worldNames.get(0).getName().equals(worldNames2.get(0).getName()));
  }

  @Test
  public void testLookups() throws IOException {
    log.info("Running WorldNamesList getWorldNameById method test");
    ObjectMapper mapper = new ObjectMapper();
    WorldNamesList worldNames = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldNames.json"),
      WorldNamesList.class
    );

    Optional<WorldName> result = worldNames.getWorldNameWithId(1009);
    assertTrue(result.isPresent());
    WorldName worldName = result.get();
    assertTrue(worldName.getName().equals("Fort Aspenwood"));

    log.info("Running WorldNamesList getWorldNameByName method test");
    result = worldNames.getWorldNameWithName("Stormbluff Isle");
    assertTrue(result.isPresent());
    worldName = result.get();
    assertTrue(worldName.getId() == 1011);
  }
}
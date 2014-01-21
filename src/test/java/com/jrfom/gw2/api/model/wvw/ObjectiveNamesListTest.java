package com.jrfom.gw2.api.model.wvw;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ObjectiveNamesListTest {
  private static final Logger log = LoggerFactory.getLogger(ObjectiveNamesListTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running ObjectiveNamesList deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    ObjectiveNamesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwObjectiveNames.json"),
      ObjectiveNamesList.class
    );

    assertTrue(list.size() == 4);
    assertTrue(list.get(0).getId() == 30);
    assertTrue(list.get(0).getName().equals("Tower"));
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running ObjectiveNamesList serialization test");
    ObjectMapper mapper = new ObjectMapper();
    ObjectiveNamesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwObjectiveNames.json"),
      ObjectiveNamesList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(list);

    ObjectiveNamesList list2 = mapper.readValue(json, ObjectiveNamesList.class);
    assertTrue(list.size() == list2.size());
    assertTrue(list.get(0).getId() == list2.get(0).getId());
    assertTrue(list.get(0).getName().equals(list2.get(0).getName()));
  }
}
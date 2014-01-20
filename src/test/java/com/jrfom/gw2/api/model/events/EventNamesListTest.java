package com.jrfom.gw2.api.model.events;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventNamesListTest {
  private static final Logger log = LoggerFactory.getLogger(EventNamesListTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running EventNamesList deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    EventNamesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/EventNames.json"),
      EventNamesList.class
    );

    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId().equals("AD31D52F-C650-473D-8637-5792868828D7"));
    assertTrue(list.get(0).getName().equals("Aid those affected by Scarlet's attack on Divinity's Reach by donating to Ho-Ho-Tron."));
    assertTrue(list.get(4).getId().equals("330BE72A-5254-4036-ACB6-7AEED05A521C"));
    assertTrue(list.get(4).getName().equals("Moa Racer Motti"));
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running EventNamesList serialization test");
    ObjectMapper mapper = new ObjectMapper();
    EventNamesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/EventNames.json"),
      EventNamesList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(list);

    EventNamesList list2 = mapper.readValue(json, EventNamesList.class);
    assertTrue(list.size() == list2.size());
    assertTrue(list.get(0).getId().equals(list2.get(0).getId()));
    assertTrue(list.get(0).getName().equals(list2.get(0).getName()));
  }

  @Test
  public void testLookups() throws IOException {
    log.info("Running EventNamesList lookup by id test");
    ObjectMapper mapper = new ObjectMapper();
    EventNamesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/EventNames.json"),
      EventNamesList.class
    );

    Optional<EventName> result = list.getEventWithId("330BE72A-5254-4036-ACB6-7AEED05A521C");
    assertTrue(result.isPresent());
    EventName eventName = result.get();
    assertTrue(eventName.getName().equals("Moa Racer Motti"));

    log.info("Running EventNamesList lookup by name test");
    result = list.getEventWithName("Mystery Moa Racer");
    assertTrue(result.isPresent());
    eventName = result.get();
    assertTrue(eventName.getId().equals("7C3DA1D6-B792-4118-9F76-4D9BC7FEC0DA"));
  }
}
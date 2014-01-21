package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.events.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventDetailsDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(EventDetailsDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running EventDetailsDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    EventDetails eventDetails = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Events.json"),
      EventDetails.class
    );

    assertTrue(eventDetails.size() == 3);

    Event event = eventDetails.get("EED8A79F-B374-4AE6-BA6F-B7B98D9D7142");
    assertTrue(event.getMapId() == 20);
    assertTrue(event.getLevel() == 42);
    assertTrue(event.getFlags().size() == 0);
    assertTrue(event.getLocation().getType().equals("sphere"));
    assertTrue(event.getLocation().getCenter().getX() == -9463.6);
    assertTrue(event.getLocation().getCenter().getY() == -40310.2);
    assertTrue(event.getLocation().getCenter().getZ() == -785.799);
    assertTrue(event.getLocation().getRadius() == 2500);
  }
}
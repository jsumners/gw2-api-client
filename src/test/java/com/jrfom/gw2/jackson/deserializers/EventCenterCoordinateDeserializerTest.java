package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.events.EventCenterCoordinate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventCenterCoordinateDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(EventCenterCoordinateDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running EventCenterCoordinateDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    EventCenterCoordinate coordinate = mapper.readValue("[-1.1, 1.1, 2.2]", EventCenterCoordinate.class);
    assertTrue(coordinate.getX() == -1.1);
    assertTrue(coordinate.getY() == 1.1);
    assertTrue(coordinate.getZ() == 2.2);
  }
}
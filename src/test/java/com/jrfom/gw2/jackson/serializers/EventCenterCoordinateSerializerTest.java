package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.events.EventCenterCoordinate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventCenterCoordinateSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(EventCenterCoordinateSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running EventCenterCoordinateSerializer test");
    EventCenterCoordinate coordinate = new EventCenterCoordinate();
    coordinate.setX(-1.1);
    coordinate.setY(1.1);
    coordinate.setZ(2.2);

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(coordinate);

    assertTrue(json.equals("[-1.1,1.1,2.2]"));
  }
}
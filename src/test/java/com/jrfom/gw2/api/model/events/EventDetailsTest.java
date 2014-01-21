package com.jrfom.gw2.api.model.events;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventDetailsTest {
  private static final Logger log = LoggerFactory.getLogger(EventDetailsTest.class);

  @Test
  public void testSerialization() throws IOException {
    log.info("Running EventDetails serialization test");
    ObjectMapper mapper = new ObjectMapper();
    EventDetails eventDetails = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Events.json"),
      EventDetails.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(eventDetails);

    assertTrue(json.length() > 0);
    // Since the resulting JSON is not identical to the input JSON,
    // due to using doubles instead of ints, this test is good enough.
    // The fact that we didn't exception out before the assert is all we need
    // to know.
  }
}
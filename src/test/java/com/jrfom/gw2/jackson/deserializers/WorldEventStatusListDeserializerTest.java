package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WorldEventStatusListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(WorldEventStatusListDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running WorldEventStatusListDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    WorldEventsStatusList statusList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldEventsStatus.json"),
      WorldEventsStatusList.class
    );

    assertTrue(statusList.size() == 5);
    assertTrue(statusList.get(0).getWorldId() == 1001);
    assertTrue(statusList.get(0).getMapId() == 39);
    assertTrue(statusList.get(0).getEventId().equals("40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81"));
    assertTrue(statusList.get(0).getState().equals("Active"));
  }
}
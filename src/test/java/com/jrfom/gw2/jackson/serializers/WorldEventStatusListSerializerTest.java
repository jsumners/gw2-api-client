package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WorldEventStatusListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(WorldEventStatusListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running WorldEventStatusListSerializer test");
    ObjectMapper mapper = new ObjectMapper();
    WorldEventsStatusList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldEventsStatus.json"),
      WorldEventsStatusList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(list);

    WorldEventsStatusList list2 = mapper.readValue(json, WorldEventsStatusList.class);
    assertTrue(list.size() == list2.size());
    assertTrue(list.get(0).getWorldId() == list2.get(0).getWorldId());
    assertTrue(list.get(0).getMapId() == list2.get(0).getMapId());
    assertTrue(list.get(0).getEventId().equals(list2.get(0).getEventId()));
    assertTrue(list.get(0).getState().equals(list2.get(0).getState()));
  }
}
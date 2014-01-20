package com.jrfom.gw2.api.model.events;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WorldEventStatusListTest {
  private static final Logger log = LoggerFactory.getLogger(WorldEventStatusListTest.class);

  private WorldEventsStatusList list;

  public WorldEventStatusListTest() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    this.list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WorldEventsStatus.json"),
      WorldEventsStatusList.class
    );
  }

  @Test
  public void testLookupEventId() {
    log.info("Running WorldEventStatusList lookup by event id test");
    ArrayList<WorldEventStatus> statuses =
      this.list.getEventsWithEventId("40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(statuses.size() == 1);

    WorldEventStatus status = statuses.get(0);
    assertTrue(status.getWorldId() == 1001);
    assertTrue(status.getMapId() == 39);
    assertTrue(status.getState().equals("Active"));
  }

  @Test
  public void testLookupMapId() {
    log.info("Running WorldEventStatusList lookup by map id test");
    ArrayList<WorldEventStatus> statuses =
      this.list.getEventsWithMapId(39);
    assertTrue(statuses.size() == 4);

    WorldEventStatus status = statuses.get(0);
    assertTrue(status.getWorldId() == 1001);
    assertTrue(status.getMapId() == 39);
    assertTrue(status.getState().equals("Active"));
  }

  @Test
  public void testLookupWorldId() {
    log.info("Running WorldEventStatusList lookup by world id test");
    ArrayList<WorldEventStatus> statuses =
      this.list.getEventsWithWorldId(1009);
    assertTrue(statuses.size() == 2);

    WorldEventStatus status = statuses.get(0);
    assertTrue(status.getWorldId() == 1009);
    assertTrue(status.getMapId() == 39);
    assertTrue(status.getState().equals("Preparation"));
    assertTrue(status.getEventId().equals("598CF3BD-0DF4-4FC7-97EA-AB4515497F5E"));
  }

  @Test
  public void testLookupByState() {
    log.info("Running WorldEventStatusList lookup by state test");
    ArrayList<WorldEventStatus> statuses =
      this.list.getEventsWithState(WorldEventStatus.STATUS_SUCCESS);
    assertTrue(statuses.size() == 1);

    WorldEventStatus status = statuses.get(0);
    assertTrue(status.getWorldId() == 1011);
    assertTrue(status.getMapId() == 40);
    assertTrue(status.getState().equals("Success"));
    assertTrue(status.getEventId().equals("A7E0F553-C4E1-452F-B39F-7BDBEC8B0BB1"));
  }

  @Test
  public void testLookupWithZeroResults() {
    log.info("Running WorldEventStatusList lookups with zero results test");
    ArrayList<WorldEventStatus> statuses =
      this.list.getEventsWithEventId("foo");
    assertTrue(statuses.size() == 0);

    statuses = this.list.getEventsWithMapId(0);
    assertTrue(statuses.size() == 0);

    statuses = this.list.getEventsWithWorldId(0);
    assertTrue(statuses.size() == 0);

    statuses = this.list.getEventsWithState("bar");
    assertTrue(statuses.size() == 0);
  }
}
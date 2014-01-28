package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventStatusesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(EventStatusesTest.class);

  @Test
  public void testEventStatuses() throws IOException {
    log.info("Running ApiClient.getEventStatuses() test");
    String expectedResponse = this.loadExpectedResponse("/json/WorldEventsStatus.json");
    this.setupMockServerSuccess("events.json", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatuses();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorld() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorld(worldId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/World.json");
    this.setupMockServerSuccess("events.json?world_id=1011", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForWorld(1011);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForMap() throws IOException {
    log.info("Running ApiClient.getEventStatusesForMap(mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Map.json");
    this.setupMockServerSuccess("events.json?map_id=40", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForMap(40);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForWorldAndMap() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndMap(worldId, mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/WorldAndMap.json");
    this.setupMockServerSuccess("events.json?world_id=1011&map_id=40", expectedResponse);

    WorldEventsStatusList list = this.apiClient.getEventStatusesForWorldAndMap(1011, 40);
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1011);
    assertTrue(list.get(0).getMapId() == 40);
    assertTrue(list.get(0).getState().equals("Success"));
  }

  @Test
  public void testEventStatusesForEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForEvent(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForEvent("40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorldAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndEvent(worldId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?world_id=1001&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForWorldAndEvent(1001, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForMapAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForMapAndEvent(mapId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?map_id=39&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForMapAndEvent(39, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }

  @Test
  public void testEventStatusesForWorldAndMapAndEvent() throws IOException {
    log.info("Running ApiClient.getEventStatusesForWorldAndMapAndEvent(worldId, mapId, eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/event-statuses/Event.json");
    this.setupMockServerSuccess(
      "events.json?world_id=1001&map_id=39&event_id=40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81",
      expectedResponse
    );

    WorldEventsStatusList list =
      this.apiClient.getEventStatusesForWorldAndMapAndEvent(1001, 39, "40B2CE06-F55B-41E5-BEEC-E5AEA1A1BC81");
    assertTrue(list.size() == 1);
    assertTrue(list.get(0).getWorldId() == 1001);
    assertTrue(list.get(0).getMapId() == 39);
    assertTrue(list.get(0).getState().equals("Active"));
  }
}
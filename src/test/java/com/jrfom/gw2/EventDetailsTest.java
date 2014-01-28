package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.events.EventDetails;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventDetailsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(EventDetailsTest.class);

  @Test
  public void testEventDetails() throws IOException {
    log.info("Running ApiClient.getEventDetails() test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    this.setupMockServerSuccess("event_details.json", expectedResponse);

    Optional<EventDetails> result = this.apiClient.getEventDetails();
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get("EED8A79F-B374-4AE6-BA6F-B7B98D9D7142")
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithId() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    String eventId = "EED8A79F-B374-4AE6-BA6F-B7B98D9D7142";
    this.setupMockServerSuccess(
      "event_details.json?event_id=" + eventId,
      expectedResponse
    );

    Optional<EventDetails> result =
      this.apiClient.getEventDetailsForEventId(eventId);
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get(eventId)
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithIdAndLang() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Events.json");
    String eventId = "EED8A79F-B374-4AE6-BA6F-B7B98D9D7142";
    this.setupMockServerSuccess(
      "event_details.json?event_id=" + eventId + "&lang=en",
      expectedResponse
    );

    Optional<EventDetails> result =
      this.apiClient.getEventDetailsForEventIdInLang(eventId, "en");
    assertTrue(result.isPresent());

    EventDetails eventDetails = result.get();
    assertTrue(eventDetails.size() == 3);
    assertTrue(
      eventDetails.get(eventId)
        .getName().equals("Defeat the renegade charr.")
    );
  }

  @Test
  public void testEventDetailsWithBadId() throws IOException {
    log.info("Running ApiClient.getEventDetailsForEventId(eventId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/EventDetails.json");
    this.setupMockServerFail("event_details.json?event_id=foo", expectedResponse);

    try {
      Optional<EventDetails> eventDetails = this.apiClient.getEventDetailsForEventId("foo");
    } catch (GwApiError error) {
      assertTrue(error.getError() == 10);
      assertTrue(error.getProduct() == 0);
      assertTrue(error.getModule() == 5);
      assertTrue(error.getLine() == 684);
      assertTrue(error.getText().equals("invalid event_id"));
    }
  }
}
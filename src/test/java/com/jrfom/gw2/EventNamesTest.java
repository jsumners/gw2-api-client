package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.events.EventNamesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class EventNamesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(EventNamesTest.class);

  @Test
  public void testEventNames() throws IOException {
    log.info("Running ApiClient.getEventNames() test");
    String expectedResponse = this.loadExpectedResponse("/json/EventNames.json");
    this.setupMockServerSuccess("event_names.json", expectedResponse);

    EventNamesList list = this.apiClient.getEventNames();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId().equals("AD31D52F-C650-473D-8637-5792868828D7"));
  }

  @Test
  public void testEventNamesForLang() throws IOException {
    log.info("Running ApiClient.getEventNamesForLang(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/EventNames.json");
    this.setupMockServerSuccess("event_names.json?lang=en", expectedResponse);

    EventNamesList list = this.apiClient.getEventNamesForLang("en");
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId().equals("AD31D52F-C650-473D-8637-5792868828D7"));
  }
}
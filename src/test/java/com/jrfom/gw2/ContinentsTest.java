package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.geography.Continents;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ContinentsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(ContinentsTest.class);

  @Test
  public void testContinents() throws IOException {
    log.info("Running ApiClient.getContinents() test");
    String expectedResponse = this.loadExpectedResponse("/json/Continents.json");
    this.setupMockServerSuccess("continents.json", expectedResponse);

    Continents continents = this.apiClient.getContinents();
    assertTrue(continents.size() == 2);
    assertTrue(continents.getContinentWithName("Tyria").isPresent());
  }
}
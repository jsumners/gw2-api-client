package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.WorldNamesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WorldNamesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(WorldNamesTest.class);

  @Test
  public void testGetWorldNames() throws IOException {
    log.info("Running ApiClient.getWorldNames() test");
    String expectedResponse = this.loadExpectedResponse("/json/WorldNames.json");
    this.setupMockServerSuccess("world_names.json", expectedResponse);

    WorldNamesList list = this.apiClient.getWorldNames();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId() == 2010);
    assertTrue(list.get(0).getName().equals("Seafarer's Rest"));
  }

  @Test
  public void testGetWorldNamesInLang() throws IOException {
    log.info("Running ApiClient.getWorldNamesInLang(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/WorldNames.json");
    this.setupMockServerSuccess("world_names.json?lang=en", expectedResponse);

    WorldNamesList list = this.apiClient.getWorldNamesInLang("en");
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getId() == 2010);
    assertTrue(list.get(0).getName().equals("Seafarer's Rest"));
  }
}
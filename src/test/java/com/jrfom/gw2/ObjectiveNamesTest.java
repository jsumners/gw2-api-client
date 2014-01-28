package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.wvw.ObjectiveNamesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ObjectiveNamesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(ObjectiveNamesTest.class);

  @Test
  public void testGetObjectiveNames() throws IOException {
    log.info("Running ApiClient.getObjectiveNamesInLang() test");
    String expectedResponse = this.loadExpectedResponse("/json/WvwObjectiveNames.json");
    this.setupMockServerSuccess("wvw/objective_names.json", expectedResponse);

    ObjectiveNamesList list = this.apiClient.getObjectiveNames();
    assertTrue(list.size() == 4);
    assertTrue(list.get(0).getId() == 30);
    assertTrue(list.get(0).getName().equals("Tower"));
  }

  @Test
  public void testGetObjectiveNamesInLang() throws IOException {
    log.info("Running ApiClient.getObjectiveNamesInLang(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/WvwObjectiveNames.json");
    this.setupMockServerSuccess("wvw/objective_names.json?lang=en", expectedResponse);

    ObjectiveNamesList list = this.apiClient.getObjectiveNamesInLang("en");
    assertTrue(list.size() == 4);
    assertTrue(list.get(0).getId() == 30);
    assertTrue(list.get(0).getName().equals("Tower"));
  }
}
package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.colors.ColorsList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ColorsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(ColorsTest.class);

  @Test
  public void testColors() throws IOException {
    log.info("Running ApiClient.getColors() test");
    String expectedResponse = this.loadExpectedResponse("/json/ColorsList.json");
    this.setupMockServerSuccess("colors.json", expectedResponse);

    ColorsList list = this.apiClient.getColors();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getColorId() == 668);
    assertTrue(list.get(0).getName().equals("Pink Ice"));


  }

  @Test
  public void testColorsWithParam() throws IOException {
    log.info("Running ApiClient.getColors(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/ColorsList.json");
    this.setupMockServerSuccess("colors.json?lang=en", expectedResponse);

    ColorsList list = this.apiClient.getColors("en");
    assertTrue(list.size() == 5);
    assertTrue(list.get(0).getColorId() == 668);
    assertTrue(list.get(0).getName().equals("Pink Ice"));
  }
}
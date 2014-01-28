package com.jrfom.gw2;

import java.io.IOException;

import com.jrfom.gw2.api.model.items.ItemIdList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ItemIdsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(ItemIdsTest.class);

  @Test
  public void testGetItemIds() throws IOException {
    log.info("Running ApiClient.getItems() test");
    String expectedResponse = this.loadExpectedResponse("/json/Items.json");
    this.setupMockServerSuccess("items.json", expectedResponse);

    ItemIdList list = this.apiClient.getItems();
    assertTrue(list.get(0).equals(12546));
  }
}
package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.items.Item;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ItemDetailsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(ItemDetailsTest.class);

  @Test
  public void testGetArmorItemDetails() throws IOException {
    log.info("Running ApiClient.getItemDetails(itemId) for armor test");
    String expectedResponse = this.loadExpectedResponse("/json/ArmorItem.json");
    this.setupMockServerSuccess("item_details.json?item_id=100", expectedResponse);

    Optional<Item> result = this.apiClient.getItemDetails(100);
    assertTrue(result.isPresent());

    Item item = result.get();
    assertTrue(item.getType().equals("Armor"));
    assertTrue(item.getArmor().getType().equals("Coat"));
  }

  @Test
  public void testGetArmorItemDetailsInLang() throws IOException {
    log.info("Running ApiClient.getItemDetailsInLang(itemId, lang) for armor test");
    String expectedResponse = this.loadExpectedResponse("/json/ArmorItem.json");
    this.setupMockServerSuccess("item_details.json?item_id=100&lang=en", expectedResponse);

    Optional<Item> result = this.apiClient.getItemDetailsInLang(100, "en");
    assertTrue(result.isPresent());

    Item item = result.get();
    assertTrue(item.getType().equals("Armor"));
    assertTrue(item.getArmor().getType().equals("Coat"));
  }

  @Test
  public void testGetInvalidItemDetails() throws IOException {
    log.info("Running ApiClient.getItemDetails(itemId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidItem.json");
    this.setupMockServerFail("item_details.json?item_id=-1", expectedResponse);

    try {
      Optional<Item> result = this.apiClient.getItemDetails(-1);
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 2);
      assertTrue(e.getLine() == 382);
      assertTrue(e.getText().equals("invalid item_id"));
    }
  }
}
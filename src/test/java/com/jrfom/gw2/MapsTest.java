package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.geography.Map;
import com.jrfom.gw2.api.model.geography.MapNamesList;
import com.jrfom.gw2.api.model.geography.MapsList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MapsTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(MapsTest.class);

  @Test
  public void testGetMap() throws IOException {
    log.info("Running ApiClient.getMap(mapId) test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/SingleMap.json");
    this.setupMockServerSuccess("maps.json?map_id=110", expectedResponse);

    Optional<Map> result = this.apiClient.getMap(110);
    assertTrue(result.isPresent());

    Map map = result.get();
    assertTrue(map.getContinentId() == 1);
    assertTrue(map.getFloors().size() == 2);
  }

  @Test
  public void testGetMapInLang() throws IOException {
    log.info("Running ApiClient.getMapInLang(mapId, lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/SingleMap.json");
    this.setupMockServerSuccess("maps.json?map_id=110&lang=en", expectedResponse);

    Optional<Map> result = this.apiClient.getMapInLang(110, "en");
    assertTrue(result.isPresent());

    Map map = result.get();
    assertTrue(map.getContinentId() == 1);
    assertTrue(map.getFloors().size() == 2);
  }

  @Test
  public void testGetBadMapId() throws IOException {
    log.info("Running ApiClient.getMap(mapId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidMap.json");
    this.setupMockServerFail("maps.json?map_id=42", expectedResponse);

    try {
      Optional<Map> result = this.apiClient.getMap(42);
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 4);
      assertTrue(e.getLine() == 228);
      assertTrue(e.getText().equals("invalid map_id"));
    }
  }

  @Test
  public void testGetMaps() throws IOException {
    log.info("Running ApiClient.getMaps() test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/MultipleMaps.json");
    this.setupMockServerSuccess("maps.json", expectedResponse);

    Optional<MapsList> result = this.apiClient.getMaps();
    assertTrue(result.isPresent());

    MapsList list = result.get();
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getContinentId() == 1);
    assertTrue(list.get(0).getFloors().size() == 2);
  }

  @Test
  public void testGetMapsInLang() throws IOException {
    log.info("Running ApiClient.getMapsInLang() test");
    String expectedResponse = this.loadExpectedResponse("/json/maps/MultipleMaps.json");
    this.setupMockServerSuccess("maps.json?lang=en", expectedResponse);

    Optional<MapsList> result = this.apiClient.getMapsInLang("en");
    assertTrue(result.isPresent());

    MapsList list = result.get();
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getContinentId() == 1);
    assertTrue(list.get(0).getFloors().size() == 2);
  }

  @Test
  public void testGetMapNames() throws IOException {
    log.info("Running ApiClient.getMapNames() test");
    String expectedResponse = this.loadExpectedResponse("/json/MapNames.json");
    this.setupMockServerSuccess("map_names.json", expectedResponse);

    MapNamesList list = this.apiClient.getMapNames();
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getId() == 50);
    assertTrue(list.get(0).getName().equals("Lion's Arch"));
  }

  @Test
  public void testGetMapNamesInLang() throws IOException {
    log.info("Running ApiClient.getMapNamesInLang(lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/MapNames.json");
    this.setupMockServerSuccess("map_names.json?lang=en", expectedResponse);

    MapNamesList list = this.apiClient.getMapNamesInLang("en");
    assertTrue(list.size() == 3);
    assertTrue(list.get(0).getId() == 50);
    assertTrue(list.get(0).getName().equals("Lion's Arch"));
  }
}
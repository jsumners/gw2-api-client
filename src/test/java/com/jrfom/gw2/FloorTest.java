package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.geography.Floor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FloorTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(FloorTest.class);

  @Test
  public void testGetTyriaFloor() throws IOException {
    log.info("Running ApiClient.getTyriaFloor(floorId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Floor.json");
    this.setupMockServerSuccess("map_floor.json?continent_id=1&floor=4", expectedResponse);

    Optional<Floor> result = this.apiClient.getTyriaFloor(4);
    assertTrue(result.isPresent());

    Floor floor = result.get();
    assertTrue(floor.getTextureDimensions().getWidth() == 32768);
    assertTrue(floor.getTextureDimensions().getHeight() == 32768);
    assertTrue(floor.getClampedView().getUpperLeft().getX().equals(12160));
    assertTrue(floor.getClampedView().getUpperLeft().getY().equals(11136));
    assertTrue(floor.getClampedView().getLowerRight().getX().equals(13696));
    assertTrue(floor.getClampedView().getLowerRight().getY().equals(12672));
    assertTrue(floor.getRegions().size() == 1);
    assertTrue(floor.getRegions().getRegionWithId(4).isPresent());
  }

  @Test
  public void testGetTyriaFloorInLang() throws IOException {
    log.info("Running ApiClient.getTyriaFloorInLang(floorId, lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/Floor.json");
    this.setupMockServerSuccess("map_floor.json?continent_id=1&floor=4&lang=en", expectedResponse);

    Optional<Floor> result = this.apiClient.getTyriaFloorInLang(4, "en");
    assertTrue(result.isPresent());

    Floor floor = result.get();
    assertTrue(floor.getTextureDimensions().getWidth() == 32768);
    assertTrue(floor.getTextureDimensions().getHeight() == 32768);
    assertTrue(floor.getClampedView().getUpperLeft().getX().equals(12160));
    assertTrue(floor.getClampedView().getUpperLeft().getY().equals(11136));
    assertTrue(floor.getClampedView().getLowerRight().getX().equals(13696));
    assertTrue(floor.getClampedView().getLowerRight().getY().equals(12672));
    assertTrue(floor.getRegions().size() == 1);
    assertTrue(floor.getRegions().getRegionWithId(4).isPresent());
  }

  @Test
  public void testGetTyriaFloorWithBadId() throws IOException {
    log.info("Running ApiClient.getTyriaFloor(floorId) failure test");
    this.setupMockServerSuccess("map_floor.json?continent_id=1&floor=-1", "{}");

    Optional<Floor> result = this.apiClient.getTyriaFloor(-1);
    assertFalse(result.isPresent());
  }
}
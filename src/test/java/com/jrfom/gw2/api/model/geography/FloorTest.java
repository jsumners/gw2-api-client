package com.jrfom.gw2.api.model.geography;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class FloorTest {
  private static final Logger log = LoggerFactory.getLogger(FloorTest.class);

  @Test
  public void testDerserialization() throws IOException {
    log.info("Running Floor deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    Floor floor = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Floor.json"),
      Floor.class
    );

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
  public void testSerialization() throws IOException {
    log.info("Running Floor serialization test");
    ObjectMapper mapper = new ObjectMapper();
    Floor floor = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Floor.json"),
      Floor.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(floor);

    Floor floor2 = mapper.readValue(json, Floor.class);
    assertTrue(floor.getTextureDimensions().getWidth() ==
      floor2.getTextureDimensions().getWidth()
    );
    assertTrue(floor.getRegions().size() == floor2.getRegions().size());
    assertTrue(floor2.getRegions().getRegionWithId(4).isPresent());
  }

  @Test
  public void testDeserializationOfBadData() throws IOException {
    log.info("Running Floor deserialization with empty data set");
    ObjectMapper mapper = new ObjectMapper();
    Floor floor = mapper.readValue("{}", Floor.class);

    assertNotNull(floor);
  }
}
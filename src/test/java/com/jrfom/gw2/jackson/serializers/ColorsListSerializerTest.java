package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.colors.Color;
import com.jrfom.gw2.api.model.colors.ColorsList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ColorsListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(ColorsListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running colors list custom Jackson serializer test");
    ObjectMapper mapper = new ObjectMapper();
    ColorsList colorsList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/ColorsList.json"),
      ColorsList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(colorsList);

    ColorsList colorsList2 = mapper.readValue(json, ColorsList.class);
    assertTrue(colorsList.size() == colorsList2.size());

    Optional<Color> result = colorsList.getColorWithName("Pink Ice");
    assertTrue(result.isPresent());
    Color color = result.get();
    result = colorsList2.getColorWithName("Pink Ice");
    assertTrue(result.isPresent());
    Color color2 = result.get();
    assertTrue(color.getName().equals(color2.getName()));
    assertTrue(color.getColorId() == color2.getColorId());
  }
}
package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.geography.Continent;
import com.jrfom.gw2.api.model.geography.Continents;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ContinentsDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(ContinentsDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running Continents deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    Continents continents = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Continents.json"),
      Continents.class
    );

    assertTrue(continents.size() == 2);

    Optional<Continent> result = continents.getContinentWithName("Tyria");
    assertTrue(result.isPresent());

    Continent continent = result.get();
    assertTrue(continent.getName().equals("Tyria"));
    assertTrue(continent.getMinZoom() == 0);
    assertTrue(continent.getMaxZoom() == 7);
    assertTrue(continent.getDimensions().size() == 2);
  }
}
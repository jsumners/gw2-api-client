package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.geography.Continent;
import com.jrfom.gw2.api.model.geography.Continents;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ContinentsSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(ContinentsSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running Continents custom serializer test");
    ObjectMapper mapper = new ObjectMapper();
    Continents continents = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Continents.json"),
      Continents.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(continents);

    Continents continents2 = mapper.readValue(json, Continents.class);
    assertTrue(continents.size() == continents2.size());

    Optional<Continent> result = continents.getContinentWithName("Tyria");
    assertTrue(result.isPresent());
    Continent continent = result.get();
    result = continents2.getContinentWithName("Tyria");
    assertTrue(result.isPresent());
    Continent continent2 = result.get();
    assertTrue(continent.getName().equals(continent2.getName()));
    assertTrue(continent.getFloors().size() == continent2.getFloors().size());
  }
}
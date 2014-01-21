package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.events.Range;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RangeDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(RangeDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running RangeDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    Range range = mapper.readValue("[-1.1, 1.1]", Range.class);
    assertTrue(range.getX() == -1.1);
    assertTrue(range.getY() == 1.1);
  }
}
package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.events.Range;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RangeSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(RangeSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running RangeSerializer test");
    Range range = new Range();
    range.setX(-1.1);
    range.setY(1.1);

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(range);

    assertTrue(json.equals("[-1.1,1.1]"));
  }
}
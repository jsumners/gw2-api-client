package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.wvw.MatchesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Instant;

import static org.junit.Assert.assertTrue;

public class MatchesListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(MatchesListDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running MatchesListDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    MatchesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwMatches.json"),
      MatchesList.class
    );

    assertTrue(list.size() == 2);
    assertTrue(list.get(0).getMatchId().equals("2-2"));
    assertTrue(list.get(0).getRedWorldId() == 2104);
    assertTrue(list.get(0).getBlueWorldId() == 2301);
    assertTrue(list.get(0).getGreenWorldId() == 2202);
    Instant instant = Instant.parse("2013-08-02T18:00:00Z");
    assertTrue(list.get(0).getStartTime().toInstant().equals(instant));
    instant = Instant.parse("2013-08-09T18:00:00Z");
    assertTrue(list.get(0).getEndTime().toInstant().equals(instant));
  }
}
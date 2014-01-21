package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.wvw.MatchesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MatchesListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(MatchesListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running MatchesListSerializer test");
    ObjectMapper mapper = new ObjectMapper();
    MatchesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwMatches.json"),
      MatchesList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(list);

    MatchesList list2 = mapper.readValue(json, MatchesList.class);
    assertTrue(list.size() == list2.size());
    assertTrue(list.get(0).getMatchId().equals(list2.get(0).getMatchId()));
    assertTrue(list.get(0).getRedWorldId() == list2.get(0).getRedWorldId());
    assertTrue(list.get(0).getBlueWorldId() == list2.get(0).getBlueWorldId());
    assertTrue(list.get(0).getGreenWorldId() == list2.get(0).getGreenWorldId());
    assertTrue(list.get(0).getStartTime().equals(list2.get(0).getStartTime()));
    assertTrue(list.get(0).getEndTime().equals(list2.get(0).getEndTime()));
  }
}
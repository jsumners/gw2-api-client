package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.wvw.MatchScores;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MatchScoresDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(MatchScoresDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running MatchScoresDeserializer test");
    ObjectMapper mapper = new ObjectMapper();
    MatchScores scores = mapper.readValue("[15, 30, 45]", MatchScores.class);

    assertTrue(scores.getRedScore() == 15);
    assertTrue(scores.getBlueScore() == 30);
    assertTrue(scores.getGreenScore() == 45);
  }
}
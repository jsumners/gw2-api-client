package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.wvw.MatchScores;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MatchScoresSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(MatchScoresSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running MatchScoresSerializer test");
    MatchScores scores = new MatchScores();
    scores.setRedScore(15);
    scores.setBlueScore(30);
    scores.setGreenScore(45);

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(scores);

    MatchScores scores2 = mapper.readValue(json, MatchScores.class);
    assertTrue(scores.getRedScore() == scores2.getRedScore());
    assertTrue(scores.getBlueScore() == scores2.getBlueScore());
    assertTrue(scores.getGreenScore() == scores2.getGreenScore());
  }
}
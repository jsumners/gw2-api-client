package com.jrfom.gw2.api.model.wvw;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MatchDetailsTest {
  private static final Logger log = LoggerFactory.getLogger(MatchDetailsTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running MatchDetails deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    MatchDetails details = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwMatchDetails.json"),
      MatchDetails.class
    );

    assertTrue(details.getMatchId().equals("1-4"));
    assertTrue(details.getScores().getRedScore() == 84925);
    assertTrue(details.getScores().getBlueScore() == 89164);
    assertTrue(details.getScores().getGreenScore() == 184617);

    WvwMap redHome = details.getMaps().get(0);
    assertTrue(redHome.getType().equals("RedHome"));
    assertTrue(redHome.getScores().getRedScore() == 34359);
    assertTrue(redHome.getScores().getBlueScore() == 12337);
    assertTrue(redHome.getScores().getGreenScore() == 29238);

    Objective objective = redHome.getObjectives().get(0);
    assertTrue(objective.getId() == 32);
    assertTrue(objective.getOwner().equals("Red"));

    objective = redHome.getObjectives().get(6);
    assertTrue(objective.getOwnerGuild().equals("B414DD70-D99D-4233-BBFF-265EC8DD6782"));
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running MatchDetails serialization test");
    ObjectMapper mapper = new ObjectMapper();
    MatchDetails details = mapper.readValue(
      this.getClass().getResourceAsStream("/json/WvwMatchDetails.json"),
      MatchDetails.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(details);

    MatchDetails details2 = mapper.readValue(json, MatchDetails.class);
    assertTrue(details.getMatchId().equals(details2.getMatchId()));
    assertTrue(details.getScores().getRedScore() == details2.getScores().getRedScore());
    assertTrue(details.getMaps().size() == details2.getMaps().size());
    assertTrue(
      details.getMaps().get(0).getObjectives().size() ==
        details2.getMaps().get(0).getObjectives().size()
    );
  }
}
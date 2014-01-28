package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.wvw.MatchDetails;
import com.jrfom.gw2.api.model.wvw.MatchesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class WvwMatchesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(WvwMatchesTest.class);

  @Test
  public void testGetMatches() throws IOException {
    log.info("Running ApiClient.getMatches() test");
    String expectedResponse = this.loadExpectedResponse("/json/WvwMatches.json");
    this.setupMockServerSuccess("wvw/matches.json", expectedResponse);

    MatchesList list = this.apiClient.getMatches();
    assertTrue(list.size() == 2);
    assertTrue(list.get(0).getMatchId().equals("2-2"));
  }

  @Test
  public void testGetMatchDetails() throws IOException {
    log.info("Running ApiClient.getMatchDetails(matchId) test");
    String expectedResponse = this.loadExpectedResponse("/json/WvwMatchDetails.json");
    this.setupMockServerSuccess("wvw/match_details.json?match_id=1-4", expectedResponse);

    Optional<MatchDetails> result = this.apiClient.getMatchDetails("1-4");
    assertTrue(result.isPresent());

    MatchDetails details = result.get();
    assertTrue(details.getMatchId().equals("1-4"));
    assertTrue(details.getScores().getRedScore() == 84925);
    assertTrue(details.getScores().getBlueScore() == 89164);
    assertTrue(details.getScores().getGreenScore() == 184617);
  }

  @Test
  public void testGetMatchDetailsFail() throws IOException {
    log.info("Running ApiClient.getMatchDetails(matchId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidMatch.json");
    this.setupMockServerFail("wvw/match_details.json?match_id=foo", expectedResponse);

    try {
      Optional<MatchDetails> result = this.apiClient.getMatchDetails("foo");
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 3);
      assertTrue(e.getLine() == 311);
      assertTrue(e.getText().equals("invalid match_id"));
    }
  }
}
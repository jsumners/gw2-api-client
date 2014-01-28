package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents World vs World match details as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/match_details">/v1/wvw/match_details</a>.
 */
@Gw2ApiVersion("v1")
public class MatchDetails {
  @JsonProperty("match_id")
  private String matchId;
  private MatchScores scores;
  private ArrayList<WvwMap> maps;

  public MatchDetails() {}

  /**
   * The unique identifier for the World vs World match.
   *
   * @return A string identifier.
   */
  public String getMatchId() {
    return this.matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }

  /**
   * The scores for the World vs World match.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.wvw.MatchScores}.
   */
  public MatchScores getScores() {
    return this.scores;
  }

  public void setScores(MatchScores scores) {
    this.scores = scores;
  }

  /**
   * A list of World vs World maps that are relevant to the match.
   *
   * @return An array of {@link com.jrfom.gw2.api.model.wvw.WvwMap} instances.
   */
  public ArrayList<WvwMap> getMaps() {
    return this.maps;
  }

  public void setMaps(ArrayList<WvwMap> maps) {
    this.maps = maps;
  }
}
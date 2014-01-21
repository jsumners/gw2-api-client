package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class MatchDetails {
  @JsonProperty("match_id")
  private String matchId;
  private MatchScores scores;
  private ArrayList<WvwMap> maps;

  public MatchDetails() {}

  public String getMatchId() {
    return this.matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }

  public MatchScores getScores() {
    return this.scores;
  }

  public void setScores(MatchScores scores) {
    this.scores = scores;
  }

  public ArrayList<WvwMap> getMaps() {
    return this.maps;
  }

  public void setMaps(ArrayList<WvwMap> maps) {
    this.maps = maps;
  }
}
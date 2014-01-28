package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MatchScoresDeserializer;
import com.jrfom.gw2.jackson.serializers.MatchScoresSerializer;

/**
 * Represents World vs World match scores as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/match_details">/v1/wvw/match_details</a>.
 */
@JsonDeserialize(using = MatchScoresDeserializer.class)
@JsonSerialize(using = MatchScoresSerializer.class)
@Gw2ApiVersion("v1")
public class MatchScores {
  private int redScore;
  private int blueScore;
  private int greenScore;

  public MatchScores() {
    this.redScore = 0;
    this.blueScore = 0;
    this.greenScore = 0;
  }

  public int getRedScore() {
    return this.redScore;
  }

  public void setRedScore(int redScore) {
    this.redScore = redScore;
  }

  public int getBlueScore() {
    return this.blueScore;
  }

  public void setBlueScore(int blueScore) {
    this.blueScore = blueScore;
  }

  public int getGreenScore() {
    return this.greenScore;
  }

  public void setGreenScore(int greenScore) {
    this.greenScore = greenScore;
  }
}
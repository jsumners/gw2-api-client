package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MatchTimeDeserializer;
import com.jrfom.gw2.jackson.serializers.MatchTimeSerializer;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Represents a match information object as returned by
 * {@code /v1/matches.json}.
 */
@Gw2ApiVersion("v1")
public class MatchInfo {
  @JsonProperty("wvw_match_id")
  private String matchId;
  @JsonProperty("red_world_id")
  private int redWorldId;
  @JsonProperty("blue_world_id")
  private int blueWorldId;
  @JsonProperty("green_world_id")
  private int greenWorldId;
  @JsonProperty("start_time")
  @JsonDeserialize(using = MatchTimeDeserializer.class)
  @JsonSerialize(using = MatchTimeSerializer.class)
  private OffsetDateTime startTime;
  @JsonProperty("end_time")
  @JsonDeserialize(using = MatchTimeDeserializer.class)
  @JsonSerialize(using = MatchTimeSerializer.class)
  private OffsetDateTime endTime;
  @JsonIgnore
  private DateTimeFormatter timeFormatter;

  public MatchInfo() {
    //this.timeFormatter = new DateTimeFormatter();
  }

  public String getMatchId() {
    return this.matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }

  public int getRedWorldId() {
    return this.redWorldId;
  }

  public void setRedWorldId(int redWorldId) {
    this.redWorldId = redWorldId;
  }

  public int getBlueWorldId() {
    return this.blueWorldId;
  }

  public void setBlueWorldId(int blueWorldId) {
    this.blueWorldId = blueWorldId;
  }

  public int getGreenWorldId() {
    return this.greenWorldId;
  }

  public void setGreenWorldId(int greenWorldId) {
    this.greenWorldId = greenWorldId;
  }

  public OffsetDateTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public void setStartTimeFromString(String startTime) {
    this.startTime = OffsetDateTime.parse(startTime);
  }

  public OffsetDateTime getEndTime() {
    return this.endTime;
  }

  public void setEndTime(OffsetDateTime endTime) {
    this.endTime = endTime;
  }

  public void setEndTimeFromString(String endTime) {
    this.endTime = OffsetDateTime.parse(endTime);
  }
}
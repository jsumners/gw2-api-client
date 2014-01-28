package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MatchTimeDeserializer;
import com.jrfom.gw2.jackson.serializers.MatchTimeSerializer;
import org.threeten.bp.OffsetDateTime;

/**
 * Represents a match information object as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/matches">/v1/wvw/matches</a>.
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

  public MatchInfo() {}

  /**
   * The unique identifier for the match.
   *
   * @return A string identifier (usually something like "1-1").
   */
  public String getMatchId() {
    return this.matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }

  /**
   * The unique id for the world that is the red team. This can be used with
   * {@link com.jrfom.gw2.api.model.WorldNamesList} to look up the name of the
   * world.
   *
   * @return An integer world id.
   */
  public int getRedWorldId() {
    return this.redWorldId;
  }

  public void setRedWorldId(int redWorldId) {
    this.redWorldId = redWorldId;
  }

  /**
   * The unique id for the world that is the blue team. This can be used with
   * {@link com.jrfom.gw2.api.model.WorldNamesList} to look up the name of the
   * world.
   *
   * @return An integer world id.
   */
  public int getBlueWorldId() {
    return this.blueWorldId;
  }

  public void setBlueWorldId(int blueWorldId) {
    this.blueWorldId = blueWorldId;
  }

  /**
   * The unique id for the world that is the green team. This can be used with
   * {@link com.jrfom.gw2.api.model.WorldNamesList} to look up the name of the
   * world.
   *
   * @return An integer world id.
   */
  public int getGreenWorldId() {
    return this.greenWorldId;
  }

  public void setGreenWorldId(int greenWorldId) {
    this.greenWorldId = greenWorldId;
  }

  /**
   * The time the World vs World match started. This time is set to UTC.
   *
   * @return An instance of {@link org.threeten.bp.OffsetDateTime}.
   */
  public OffsetDateTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public void setStartTimeFromString(String startTime) {
    this.startTime = OffsetDateTime.parse(startTime);
  }

  /**
   * The time when the match is scheduled to end. This time is set to UTC.
   *
   * @return An instance of {@link org.threeten.bp.OffsetDateTime}.
   */
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
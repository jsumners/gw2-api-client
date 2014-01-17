package com.jrfom.gw2.api.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class GuildEmblem {
  @JsonProperty("background_id")
  private int backgroundId;
  @JsonProperty("foreground_id")
  private int foregroundId;
  private ArrayList<String> flags;
  @JsonProperty("background_color_id")
  private int backgroundColorId;
  @JsonProperty("foreground_primary_color_id")
  private int foregroundPrimaryColorId;
  @JsonProperty("foreground_secondary_color_id")
  private int foregroundSecondaryColorId;

  public GuildEmblem() {}

  public int getBackgroundId() {
    return this.backgroundId;
  }

  public void setBackgroundId(int backgroundId) {
    this.backgroundId = backgroundId;
  }

  public int getForegroundId() {
    return this.foregroundId;
  }

  public void setForegroundId(int foregroundId) {
    this.foregroundId = foregroundId;
  }

  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  public int getBackgroundColorId() {
    return this.backgroundColorId;
  }

  public void setBackgroundColorId(int backgroundColorId) {
    this.backgroundColorId = backgroundColorId;
  }

  public int getForegroundPrimaryColorId() {
    return this.foregroundPrimaryColorId;
  }

  public void setForegroundPrimaryColorId(int foregroundPrimaryColorId) {
    this.foregroundPrimaryColorId = foregroundPrimaryColorId;
  }

  public int getForegroundSecondaryColorId() {
    return this.foregroundSecondaryColorId;
  }

  public void setForegroundSecondaryColorId(int foregroundSecondaryColorId) {
    this.foregroundSecondaryColorId = foregroundSecondaryColorId;
  }
}
package com.jrfom.gw2.api.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a guild's in-game emblem as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/guild_details">/v1/guild_details</a>.
 */
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

  // TODO: update this documentation with a link to the "files" API object(s)
  /**
   * Retrieve the identifier for the emblem's background image.
   *
   * @return An integer that identifies the background image.
   */
  public int getBackgroundId() {
    return this.backgroundId;
  }

  public void setBackgroundId(int backgroundId) {
    this.backgroundId = backgroundId;
  }

  // TODO: update this documentation with a link to the "files" API object(s)
  /**
   * Retrieve the identifier for the emblem's foreground image.
   *
   * @return An integer that identifies the foreground image.
   */
  public int getForegroundId() {
    return this.foregroundId;
  }

  public void setForegroundId(int foregroundId) {
    this.foregroundId = foregroundId;
  }

  /**
   * <p>Retrieve a list of flags that further describe the emblem. Possible
   * flag values are:</p>
   *
   * <ul>
   *   <li>FlipBackgroundHorizontal</li>
   *   <li>FlipBackgroundVertical</li>
   *   <li>FlipForegroundHorizontal</li>
   *   <li>FlipForegroundVertical</li>
   * </ul>
   *
   * @return The list of flags or an empty list.
   */
  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  /**
   * Retrieve the identifier for the emblem's background
   * {@link com.jrfom.gw2.api.model.colors.Color}.
   *
   * @return An integer that identifies the background color.
   */
  public int getBackgroundColorId() {
    return this.backgroundColorId;
  }

  public void setBackgroundColorId(int backgroundColorId) {
    this.backgroundColorId = backgroundColorId;
  }

  /**
   * Retrieve the identifier for the emblem's primary
   * {@link com.jrfom.gw2.api.model.colors.Color}.
   *
   * @return An integer that identifies the primary color.
   */
  public int getForegroundPrimaryColorId() {
    return this.foregroundPrimaryColorId;
  }

  public void setForegroundPrimaryColorId(int foregroundPrimaryColorId) {
    this.foregroundPrimaryColorId = foregroundPrimaryColorId;
  }

  /**
   * Retrieve the identifier for the emblem's secondary
   * {@link com.jrfom.gw2.api.model.colors.Color}.
   *
   * @return An integer that identifies the secondary color.
   */
  public int getForegroundSecondaryColorId() {
    return this.foregroundSecondaryColorId;
  }

  public void setForegroundSecondaryColorId(int foregroundSecondaryColorId) {
    this.foregroundSecondaryColorId = foregroundSecondaryColorId;
  }
}
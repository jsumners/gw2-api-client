package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents guild details as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/guild_details">/v1/guild_details</a>.
 */
@Gw2ApiVersion("v1")
public class Guild {
  @JsonProperty("guild_id")
  private String id;
  @JsonProperty("guild_name")
  private String name;
  private String tag;
  private GuildEmblem emblem;

  public Guild() {}

  /**
   * Retrieve the UUID for the guild.
   *
   * @return A string representation of the guild's UUID.
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Retrieve the guild's full in-game name.
   *
   * @return A string representing the guild's name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieve the guild's in-game tag.
   *
   * @return A string representing the guild's tag.
   */
  public String getTag() {
    return this.tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  /**
   * Retrieve details about the guild's in-game emblem.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.GuildEmblem}.
   */
  public GuildEmblem getEmblem() {
    return this.emblem;
  }

  public void setEmblem(GuildEmblem emblem) {
    this.emblem = emblem;
  }
}
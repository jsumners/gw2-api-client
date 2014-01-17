package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class Guild {
  @JsonProperty("guild_id")
  private String id;
  @JsonProperty("guild_name")
  private String name;
  private String tag;
  private GuildEmblem emblem;

  public Guild() {}

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTag() {
    return this.tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public GuildEmblem getEmblem() {
    return this.emblem;
  }

  public void setEmblem(GuildEmblem emblem) {
    this.emblem = emblem;
  }
}
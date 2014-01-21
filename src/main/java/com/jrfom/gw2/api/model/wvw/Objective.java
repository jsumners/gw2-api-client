package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class Objective {
  private int id;
  private String owner;
  @JsonProperty("owner_guild")
  private String ownerGuild;

  public Objective() {}

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOwner() {
    return this.owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getOwnerGuild() {
    return this.ownerGuild;
  }

  public void setOwnerGuild(String ownerGuild) {
    this.ownerGuild = ownerGuild;
  }
}
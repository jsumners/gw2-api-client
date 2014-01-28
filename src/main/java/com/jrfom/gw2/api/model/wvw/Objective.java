package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an in-game World vs World objective.
 */
@Gw2ApiVersion("v1")
public class Objective {
  private int id;
  private String owner;
  @JsonProperty("owner_guild")
  private String ownerGuild;

  public Objective() {}

  /**
   * The unique id for the objective.
   *
   * @return An integer identifier.
   */
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * The current owner of the objective. Can be "Red", "Blue", "Green", or
   * "Neutral".
   *
   * @return A string owner.
   */
  public String getOwner() {
    return this.owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * If a guild has claimed the objective this will return the unique id
   * for the guild. See {@link com.jrfom.gw2.api.model.Guild} for more details.
   *
   * @return A string guild identifier.
   */
  public String getOwnerGuild() {
    return this.ownerGuild;
  }

  public void setOwnerGuild(String ownerGuild) {
    this.ownerGuild = ownerGuild;
  }
}
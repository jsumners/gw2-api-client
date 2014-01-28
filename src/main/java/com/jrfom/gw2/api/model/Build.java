package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a game build number as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/build">/v1/build</a>.
 */
@Gw2ApiVersion("v1")
public class Build {
  @JsonProperty("build_id")
  private int buildId;

  public Build() {}

  public Build(int buildId) {
    this.buildId = buildId;
  }

  /**
   * The game build number.
   *
   * @return An integer build number.
   */
  public int getBuildId() {
    return this.buildId;
  }

  public void setBuildId(int buildId) {
    this.buildId = buildId;
  }
}
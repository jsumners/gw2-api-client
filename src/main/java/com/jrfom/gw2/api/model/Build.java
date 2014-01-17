package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * <a href="http://wiki.guildwars2.com/wiki/API:1/build">
 *   http://wiki.guildwars2.com/wiki/API:1/build
 * </a>
 */
@Gw2ApiVersion("v1")
public class Build {
  @JsonProperty("build_id")
  private String buildId;

  public Build(String buildId) {
    this.buildId = buildId;
  }

  public String getBuildId() {
    return this.buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }
}
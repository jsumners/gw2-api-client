package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the Borderlands Bloodlust bonus for World vs World.
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class BloodlustBonus implements Bonus {
  protected String type;
  protected String owner;

  public BloodlustBonus() {
    this.type = "bloodlust";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getOwner() {
    return this.owner;
  }
}
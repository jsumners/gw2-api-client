package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class BloodlustBonus implements Bonus {
  protected String type;
  protected String owner;

  public BloodlustBonus() {
    this.type = "bloodlust";
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public String getOwner() {
    return this.owner;
  }
}
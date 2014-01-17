package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class WeaponItemProperties extends ItemProperties {
  @JsonProperty("damage_type")
  private String damageType;
  @JsonProperty("min_power")
  private int minPower;
  @JsonProperty("max_power")
  private int maxPower;
  private int defense;

  public WeaponItemProperties() {}

  public String getDamageType() {
    return this.damageType;
  }

  public void setDamageType(String damageType) {
    this.damageType = damageType;
  }

  public int getMinPower() {
    return this.minPower;
  }

  public void setMinPower(int minPower) {
    this.minPower = minPower;
  }

  public int getMaxPower() {
    return this.maxPower;
  }

  public void setMaxPower(int maxPower) {
    this.maxPower = maxPower;
  }

  public int getDefense() {
    return this.defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }
}
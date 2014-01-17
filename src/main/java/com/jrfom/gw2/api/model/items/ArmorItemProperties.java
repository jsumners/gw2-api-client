package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class ArmorItemProperties extends ItemProperties {
  @JsonProperty("weight_class")
  private String weightClass;
  private int defense;

  public ArmorItemProperties() {}

  public String getWeightClass() {
    return this.weightClass;
  }

  public void setWeightClass(String weightClass) {
    this.weightClass = weightClass;
  }

  public int getDefense() {
    return this.defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }
}
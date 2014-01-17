package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpgradeComponentItemProperties extends ItemProperties {
  private ArrayList<String> flags;
  @JsonProperty("infusion_upgrade_flags")
  private ArrayList<String> infusionUpgradeFlags;
  private String suffix;

  public UpgradeComponentItemProperties() {}

  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  public ArrayList<String> getInfusionUpgradeFlags() {
    return this.infusionUpgradeFlags;
  }

  public void setInfusionUpgradeFlags(ArrayList<String> infusionUpgradeFlags) {
    this.infusionUpgradeFlags = infusionUpgradeFlags;
  }

  public String getSuffix() {
    return this.suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
}
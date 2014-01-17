package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ItemProperties {
  private String type;
  @JsonProperty("infusion_slots")
  private ArrayList<ArrayList<String>> infusionSlots;
  @JsonProperty("infix_upgrade")
  private InfixUpgrade infixUpgrade;
  @JsonProperty("suffix_item_id")
  private int suffixItemId;

  public ItemProperties() {}

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<ArrayList<String>> getInfusionSlots() {
    return this.infusionSlots;
  }

  public void setInfusionSlots(ArrayList<ArrayList<String>> infusionSlots) {
    this.infusionSlots = infusionSlots;
  }

  public InfixUpgrade getInfixUpgrade() {
    return this.infixUpgrade;
  }

  public void setInfixUpgrade(InfixUpgrade infixUpgrade) {
    this.infixUpgrade = infixUpgrade;
  }

  public int getSuffixItemId() {
    return this.suffixItemId;
  }

  public void setSuffixItemId(int suffixItemId) {
    this.suffixItemId = suffixItemId;
  }
}
package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumableItemProperties {
  private String type;
  @JsonProperty("unlock_type")
  private String unlockType;
  @JsonProperty("recipe_id")
  private int recipeId;

  public ConsumableItemProperties() {}

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUnlockType() {
    return this.unlockType;
  }

  public void setUnlockType(String unlockType) {
    this.unlockType = unlockType;
  }

  public int getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
}
package com.jrfom.gw2.api.model.crafting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an in-game crafting ingredient. This is used by
 * {@link com.jrfom.gw2.api.model.crafting.Recipe}.
 */
@Gw2ApiVersion("v1")
public class Ingredient {
  @JsonProperty("item_id")
  private int itemId;
  private int count;

  public Ingredient() {}

  /**
   * The unique identifier for the crafting ingredient.
   *
   * @return An integer identifier.
   */
  public int getItemId() {
    return this.itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  /**
   * How many of this ingredient is needed in the
   * {@link com.jrfom.gw2.api.model.crafting.Recipe}.
   *
   * @return An integer count.
   */
  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
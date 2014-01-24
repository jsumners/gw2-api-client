package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a set of properties that are specific to a
 * {@link com.jrfom.gw2.api.model.items.ConsumableItem}.
 */
@Gw2ApiVersion("v1")
public class ConsumableItemProperties {
  private String type;
  @JsonProperty("unlock_type")
  private String unlockType;
  @JsonProperty("recipe_id")
  private int recipeId;

  public ConsumableItemProperties() {}

  /**
   * <p>Retrieve the type of the consumable. Possible values are:</p>
   *
   * <ul>
   *   <li>Unlock</li>
   *   <li>AppearanceChange</li>
   *   <li>ContractNpc</li>
   *   <li>Food</li>
   *   <li>Booze</li>
   *   <li>Generic</li>
   *   <li>Halloween</li>
   *   <li>Immediate</li>
   *   <li>Transmutation</li>
   *   <li>Utility</li>
   * </ul>
   *
   * @return A string identifying the consumable type.
   */
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * If the consumable is of type "Unlock", this details what sort of
   * unlock.
   *
   * @return A string identifying the unlock type, e.g. "CraftingRecipe".
   */
  public String getUnlockType() {
    return this.unlockType;
  }

  public void setUnlockType(String unlockType) {
    this.unlockType = unlockType;
  }

  /**
   * If the consumable unlocks a recipe, this identifies the recipe that will
   * be unlocked.
   *
   * @return A unique {@link com.jrfom.gw2.api.model.crafting.Recipe} id.
   */
  public int getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
}
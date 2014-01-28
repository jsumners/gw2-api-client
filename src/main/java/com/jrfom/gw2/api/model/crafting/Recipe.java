package com.jrfom.gw2.api.model.crafting;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;

/**
 * Represents an in-game crafting recipe as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/recipe_details">/v1/recipe_details</a>.
 */
@Gw2ApiVersion("v1")
public class Recipe {
  @JsonProperty("recipe_id")
  private int recipeId;
  private String type;
  @JsonProperty("output_item_id")
  private int outputItemId;
  @JsonProperty("output_item_count")
  private int outputItemCount;
  @JsonProperty("min_rating")
  private int minRating;
  @JsonProperty("time_to_craft_ms")
  private long timeToCraft;
  @JsonProperty("vendor_value")
  private Coins vendorValue;
  private ArrayList<String> disciplines;
  private ArrayList<String> flags;
  private ArrayList<Ingredient> ingredients;

  public Recipe() {}

  /**
   * The unique identifier for the recipe.
   *
   * @return An integer identifier.
   */
  public int getRecipeId() {
    return this.recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }

  /**
   * The type of item produced by the recipe. For example, "Coat" for a
   * recipe that crafts {@link com.jrfom.gw2.api.model.crafting.Ingredient}s
   * into a coat.
   *
   * @return A string type.
   */
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * The unique identifier for the resulting
   * {@link com.jrfom.gw2.api.model.items.Item}.
   *
   * @return An integer identifier.
   */
  public int getOutputItemId() {
    return this.outputItemId;
  }

  public void setOutputItemId(int outputItemId) {
    this.outputItemId = outputItemId;
  }

  /**
   * The number of an {@link com.jrfom.gw2.api.model.items.Item} that crafting
   * this recipe will produce.
   *
   * @return An integer item count.
   */
  public int getOutputItemCount() {
    return this.outputItemCount;
  }

  public void setOutputItemCount(int outputItemCount) {
    this.outputItemCount = outputItemCount;
  }

  /**
   * The minimum crafting skill rating required to craft the recipe.
   *
   * @return An integer skill rating.
   */
  public int getMinRating() {
    return this.minRating;
  }

  public void setMinRating(int minRating) {
    this.minRating = minRating;
  }

  /**
   * The time it will take to craft the recipe. This is measured in
   * milliseconds.
   *
   * @return A long integer time for crafting.
   */
  public long getTimeToCraft() {
    return this.timeToCraft;
  }

  public void setTimeToCraft(long timeToCraft) {
    this.timeToCraft = timeToCraft;
  }

  /**
   * The amount of in-game currency the recipe is worth when selling to an
   * in-game vendor.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Coins}.
   */
  public Coins getVendorValue() {
    return this.vendorValue;
  }

  public void setVendorValue(Coins vendorValue) {
    this.vendorValue = vendorValue;
  }

  /**
   * The list of crafting disciplines (e.g. "Armorsmith" or "Weaponsmith") that
   * is allowed to use the recipe.
   *
   * @return An array of string disciplines.
   */
  public ArrayList<String> getDisciplines() {
    return this.disciplines;
  }

  public void setDisciplines(ArrayList<String> disciplines) {
    this.disciplines = disciplines;
  }

  /**
   * Extra details about the recipe.
   *
   * @return An array of string flags.
   */
  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  /**
   * A list of {@link com.jrfom.gw2.api.model.crafting.Ingredient}s required
   * to craft the recipe.
   *
   * @return An array of {@link com.jrfom.gw2.api.model.crafting.Ingredient}
   * instances.
   */
  public ArrayList<Ingredient> getIngredients() {
    return this.ingredients;
  }

  public void setIngredients(ArrayList<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
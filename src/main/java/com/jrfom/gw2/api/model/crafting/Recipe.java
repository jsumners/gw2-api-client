package com.jrfom.gw2.api.model.crafting;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;

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

  public int getRecipeId() {
    return this.recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getOutputItemId() {
    return this.outputItemId;
  }

  public void setOutputItemId(int outputItemId) {
    this.outputItemId = outputItemId;
  }

  public int getOutputItemCount() {
    return this.outputItemCount;
  }

  public void setOutputItemCount(int outputItemCount) {
    this.outputItemCount = outputItemCount;
  }

  public int getMinRating() {
    return this.minRating;
  }

  public void setMinRating(int minRating) {
    this.minRating = minRating;
  }

  public long getTimeToCraft() {
    return this.timeToCraft;
  }

  public void setTimeToCraft(long timeToCraft) {
    this.timeToCraft = timeToCraft;
  }

  public Coins getVendorValue() {
    return this.vendorValue;
  }

  public void setVendorValue(Coins vendorValue) {
    this.vendorValue = vendorValue;
  }

  public ArrayList<String> getDisciplines() {
    return this.disciplines;
  }

  public void setDisciplines(ArrayList<String> disciplines) {
    this.disciplines = disciplines;
  }

  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  public ArrayList<Ingredient> getIngredients() {
    return this.ingredients;
  }

  public void setIngredients(ArrayList<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
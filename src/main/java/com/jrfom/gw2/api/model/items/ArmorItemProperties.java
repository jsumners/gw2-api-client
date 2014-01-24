package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the properties that are specific to an in-game armor item.
 */
@Gw2ApiVersion("v1")
public class ArmorItemProperties extends ItemProperties {
  @JsonProperty("weight_class")
  private String weightClass;
  private int defense;

  public ArmorItemProperties() {}

  /**
   * <p>A string indicating what sort of armor the item is. For example,
   * "Light" for light armor and "Heavy" for heavy armor.</p>
   *
   * <p>The possible values are:</p>
   *
   * <ul>
   *   <li>Medium</li>
   *   <li>Light</li>
   *   <li>Heavy</li>
   *   <li>Clothing</li>
   * </ul>
   *
   * @return A string representing the weight class.
   */
  public String getWeightClass() {
    return this.weightClass;
  }

  public void setWeightClass(String weightClass) {
    this.weightClass = weightClass;
  }

  /**
   * The amount of basic defense the item provides.
   *
   * @return An integer indicating the defense rating of the item.
   */
  public int getDefense() {
    return this.defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }
}
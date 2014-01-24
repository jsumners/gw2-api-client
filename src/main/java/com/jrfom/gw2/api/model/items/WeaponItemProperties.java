package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a set of properties that are specific to a
 * {@link com.jrfom.gw2.api.model.items.WeaponItem}.
 */
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

  /**
   * <p>Details what sort of damage the weapon will inflict. Possible
   * values are:</p>
   *
   * <ul>
   *   <li>Physical</li>
   *   <li>Fire</li>
   *   <li>Ice</li>
   *   <li>Lightning</li>
   * </ul>
   *
   * @return The type of weapon damage as a string.
   */
  public String getDamageType() {
    return this.damageType;
  }

  public void setDamageType(String damageType) {
    this.damageType = damageType;
  }

  /**
   * The minimum damage the weapon will inflict.
   *
   * @return An integer representing the minimum damage.
   */
  public int getMinPower() {
    return this.minPower;
  }

  public void setMinPower(int minPower) {
    this.minPower = minPower;
  }

  /**
   * The maximum damage the weapon will inflict.
   *
   * @return An integer representing the maximum damage.
   */
  public int getMaxPower() {
    return this.maxPower;
  }

  public void setMaxPower(int maxPower) {
    this.maxPower = maxPower;
  }

  /**
   * The amount of defense the weapon will provide (e.g a shield).
   *
   * @return An integer representing the defense rating of the weapon.
   */
  public int getDefense() {
    return this.defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }
}
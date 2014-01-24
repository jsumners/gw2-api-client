package com.jrfom.gw2.api.model.items;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an {@link com.jrfom.gw2.api.model.items.InfixUpgrade} attribute.
 */
@Gw2ApiVersion("v1")
public class UpgradeAttribute {
  private String attribute;
  private int modifier;

  public UpgradeAttribute() {}

  /**
   * The type of upgrade that is will be applied, e.g. "Power" or "Toughness".
   *
   * @return A string name of the upgrade attribute.
   */
  public String getAttribute() {
    return this.attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  /**
   * The amount of the upgrade, e.g. 100 for a +100 bonus.
   *
   * @return An integer representing the amount of the upgrade bonus.
   */
  public int getModifier() {
    return this.modifier;
  }

  public void setModifier(int modifier) {
    this.modifier = modifier;
  }
}
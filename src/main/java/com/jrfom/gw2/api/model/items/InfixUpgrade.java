package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents permanent upgrades to an item. For example, in-game armor that has
 * "Power", "Toughness", and "Vitality" upgrades would have an
 * {@code InfixUpgrade} property that has three attributes.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfixUpgrade {
  private ArrayList<UpgradeAttribute> attributes;
  private UpgradeBuff buff;

  public InfixUpgrade() {}

  /**
   * Retrieve a list of the upgrade attributes the
   * {@link com.jrfom.gw2.api.model.items.InfixUpgrade} applies.
   *
   * @return A list of {@link com.jrfom.gw2.api.model.items.UpgradeAttribute}s.
   */
  public ArrayList<UpgradeAttribute> getAttributes() {
    return this.attributes;
  }

  public void setAttributes(ArrayList<UpgradeAttribute> attributes) {
    this.attributes = attributes;
  }

  /**
   * Retrieve details on any skill buff the
   * {@link com.jrfom.gw2.api.model.items.InfixUpgrade} applies. For example,
   * "+1 Agony Resistance".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.UpgradeBuff}.
   */
  public UpgradeBuff getBuff() {
    return this.buff;
  }

  public void setBuff(UpgradeBuff buff) {
    this.buff = buff;
  }
}
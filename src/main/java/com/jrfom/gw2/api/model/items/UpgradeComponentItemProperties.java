package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents properties specific to an
 * {@link com.jrfom.gw2.api.model.items.UpgradeComponentItem}.
 */
@Gw2ApiVersion("v1")
public class UpgradeComponentItemProperties extends ItemProperties {
  private ArrayList<String> flags;
  @JsonProperty("infusion_upgrade_flags")
  private ArrayList<String> infusionUpgradeFlags;
  private String suffix;

  public UpgradeComponentItemProperties() {}

  /**
   * <p>Retrieve a list of flags that indicate what items the upgrade component
   * may be applied to.</p>
   *
   * <p>Possible values are:</p>
   *
   * <ul>
   *   <li>HeavyArmor</li>
   *   <li>MediumArmor</li>
   *   <li>LightArmor</li>
   *   <li>Axe</li>
   *   <li>Dagger</li>
   *   <li>Focus</li>
   *   <li>Greatsword</li>
   *   <li>Hammer</li>
   *   <li>Harpoon</li>
   *   <li>LongBow</li>
   *   <li>Mace</li>
   *   <li>Pistol</li>
   *   <li>Rifle</li>
   *   <li>Scepter</li>
   *   <li>Shield</li>
   *   <li>ShortBow</li>
   *   <li>Speargun</li>
   *   <li>Staff</li>
   *   <li>Torch</li>
   *   <li>Trident</li>
   *   <li>Trinket</li>
   *   <li>Warhorn</li>
   * </ul>
   *
   * @return An array of strings naming item types that the component can
   * upgrade.
   */
  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  /**
   * <p>A list of infusion slots that the upgrade component could be applied to.
   * Possible values are:</p>
   *
   * <ul>
   *   <li>Defense</li>
   *   <li>Offense</li>
   *   <li>Utility</li>
   * </ul>
   *
   * @return A an array of strings detailing the infusion slot flags.
   */
  public ArrayList<String> getInfusionUpgradeFlags() {
    return this.infusionUpgradeFlags;
  }

  public void setInfusionUpgradeFlags(ArrayList<String> infusionUpgradeFlags) {
    this.infusionUpgradeFlags = infusionUpgradeFlags;
  }

  /**
   * The suffix that will be appened to the item once the upgrade component is
   * applied. For example, "of the Rata Sum".
   *
   * @return The suffix string.
   */
  public String getSuffix() {
    return this.suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
}
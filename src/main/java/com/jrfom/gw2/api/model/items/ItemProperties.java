package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Represents a set of properties that are common to multiple item types.
 * These properties are shared amongst the following item types:</p>
 *
 * <ul>
 *   <li>{@link com.jrfom.gw2.api.model.items.BagItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.ConsumableItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.ContainerItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.GizmoItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.TrinketItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.UpgradeComponentItem}</li>
 *   <li>{@link com.jrfom.gw2.api.model.items.WeaponItem}</li>
 * </ul>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ItemProperties {
  private String type;
  @JsonProperty("infusion_slots")
  private ArrayList<ArrayList<String>> infusionSlots;
  @JsonProperty("infix_upgrade")
  private InfixUpgrade infixUpgrade;
  @JsonProperty("suffix_item_id")
  private int suffixItemId;

  public ItemProperties() {}

  // TODO: add missing "Back Types"
  /**
   * <p>The specific type for this item. It various amongst different items. See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/item_details">/v1/item_details</a>
   * for more information (and its discussion page).</p>
   *
   * <dl>
   *   <dt>Armor Types</dt>
   *   <dd>Boots</dd>
   *   <dd>Helm</dd>
   *   <dd>Leggings</dd>
   *   <dd>Gloves</dd>
   *   <dd>Shoulders</dd>
   *   <dd>Coat</dd>
   *   <dd>HelmAquatic</dd>
   *
   *   <dt>Consumable Types</dt>
   *   <dd>Unlock</dd>
   *   <dd>AppearanceChange</dd>
   *   <dd>ContractNpc</dd>
   *   <dd>Food</dd>
   *   <dd>Booze</dd>
   *   <dd>Generic</dd>
   *   <dd>Halloween</dd>
   *   <dd>Immediate</dd>
   *   <dd>Transmutation</dd>
   *   <dd>Utility</dd>
   *
   *   <dt>Container Types</dt>
   *   <dd>Default</dd>
   *   <dd>Giftbox</dd>
   *
   *   <dt>Gathering Types</dt>
   *   <dd>Logging</dd>
   *   <dd>Foraging</dd>
   *   <dd>Mining</dd>
   *
   *   <dt>Gizmo Types</dt>
   *   <dd>Default</dd>
   *   <dd>RentableContractNPC</dd>
   *   <dd>UnlimitedConsumable</dd>
   *
   *   <dt>Tool Types</dt>
   *   <dd>Salvage</dd>
   *
   *   <dt>Trinket Types</dt>
   *   <dd>Ring</dd>
   *   <dd>Accessory</dd>
   *   <dd>Amulet</dd>
   *
   *   <dt>Upgrade Component Types</dt>
   *   <dd>Rune</dd>
   *   <dd>Default</dd>
   *   <dd>Sigil</dd>
   *   <dd>Gem</dd>
   *
   *   <dt>Weapon Types</dt>
   *   <dd>LongBow</dd>
   *   <dd>Pistol</dd>
   *   <dd>Warhorn</dd>
   *   <dd>Sword</dd>
   *   <dd>Staff</dd>
   *   <dd>Hammer</dd>
   *   <dd>Trident</dd>
   *   <dd>Scepter</dd>
   *   <dd>Speargun</dd>
   *   <dd>Mace</dd>
   *   <dd>Axe</dd>
   *   <dd>Torch</dd>
   *   <dd>Dagger</dd>
   *   <dd>Shield</dd>
   *   <dd>Harpoon</dd>
   *   <dd>Greatsword</dd>
   *   <dd>Rifle</dd>
   *   <dd>Focus</dd>
   *   <dd>ShortBow</dd>
   *   <dd>Toy</dd>
   *   <dd>TwoHandedToy</dd>
   * </dl>
   *
   * @return A string detailing the specific item type. E.g. "Coat" for an armor
   * item that is a coat.
   */
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * <p>An array of infusion slots available on the item. Each infusion slot is
   * represented by an array of flags that indicate the type of infusions
   * possible in that slot. For example, if an item has three infusion slots,
   * then this will return an array of three arrays.</p>
   *
   * <p>The possible flags for each infusion slot are:</p>
   *
   * <ul>
   *   <li>Defense</li>
   *   <li>Offense</li>
   *   <li>Utility</li>
   * </ul>
   *
   * @return A list of lists indicating the possible infusion slots and their
   * infusion types. Otherwise, and empty list.
   */
  public ArrayList<ArrayList<String>> getInfusionSlots() {
    return this.infusionSlots;
  }

  public void setInfusionSlots(ArrayList<ArrayList<String>> infusionSlots) {
    this.infusionSlots = infusionSlots;
  }

  /**
   * Indicates which permanent upgrades are applied to the item.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.InfixUpgrade}.
   */
  public InfixUpgrade getInfixUpgrade() {
    return this.infixUpgrade;
  }

  public void setInfixUpgrade(InfixUpgrade infixUpgrade) {
    this.infixUpgrade = infixUpgrade;
  }

  /**
   * An identifier for an item that has already been applied to this item as
   * an upgrade.
   *
   * @return An integer identifying the upgrade component.
   */
  public int getSuffixItemId() {
    return this.suffixItemId;
  }

  public void setSuffixItemId(int suffixItemId) {
    this.suffixItemId = suffixItemId;
  }
}
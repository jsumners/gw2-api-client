package com.jrfom.gw2.api.model.items;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;

/**
 * <p>Represents details of an in-game item as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/item_details">/v1/item_details</a>.</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Gw2ApiVersion("v1")
public interface Item {
  /**
   * The unique identifier for the item. This is used throughout the rest
   * of the API to reference the item.
   *
   * @return An integer identifier.
   */
  @JsonProperty("item_id")
  Integer getItemId();

  /**
   * The in-game name of the item.
   *
   * @return The string that labels the item in-game.
   */
  @JsonProperty("name")
  String getName();

  /**
   * The in-game description of the item, if one exists.
   *
   * @return A localized description of the item or an empty string.
   */
  @JsonProperty("description")
  String getDescription();

  /**
   * <p>The specific kind of item. Possible values are:</p>
   *
   * <ul>
   *   <li>Armor</li>
   *   <li>Bag</li>
   *   <li>Consumable</li>
   *   <li>Container</li>
   *   <li>CraftingMaterial</li>
   *   <li>Gizmo</li>
   *   <li>MiniPet</li>
   *   <li>Trinket</li>
   *   <li>Trophy</li>
   *   <li>UpgradeComponent</li>
   *   <li>Weapon</li>
   * </ul>
   *
   * <p>Each of these types is represented by it's on POJO that is an
   * implementation of {@link com.jrfom.gw2.api.model.items.Item}. Each POJO is
   * named in the format {@code TypeItem}. For example, an "Armor" item is
   * represented by the {@link com.jrfom.gw2.api.model.items.ArmorItem} object.</p>
   * @return
   */
  @JsonProperty("type")
  String getType();

  /**
   * The character level required to be able to use this item.
   *
   * @return An integer representing the required character level.
   */
  @JsonProperty("level")
  Integer getLevel();

  /**
   * <p>The in-game rarity level of the item. The possible values are:</p>
   *
   * <ul>
   *   <li>Ascended</li>
   *   <li>Basic</li>
   *   <li>Exotic</li>
   *   <li>Fine</li>
   *   <li>Junk</li>
   *   <li>Legendary</li>
   *   <li>Masterwork</li>
   *   <li>Rare</li>
   * </ul>
   *
   * @return A string representing the rarity level of the item.
   */
  @JsonProperty("rarity")
  String getRarity();

  /**
   * The amount of in-game currency for which the item can be sold to a vendor.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Coins}.
   */
  @JsonProperty("vendor_value")
  Coins getVendorValue();

  /**
   * The file identifier for use with the
   * <a href="http://wiki.guildwars2.com/wiki/API:Render_service>render service</a>.
   *
   * @return A numeric file id.
   */
  @JsonProperty("icon_file_id")
  Integer getIconFileId();

  /**
   * The file signature for use with the
   * <a href="http://wiki.guildwars2.com/wiki/API:Render_service>render service</a>.
   *
   * @return A string file signature.
   */
  @JsonProperty("icon_file_signature")
  String getIconFileSignature();

  /**
   * <p>A list of the game modes where the item can be used. The
   * possible values are:</p>
   *
   * <ul>
   *   <li>Activity</li>
   *   <li>Dungeon</li>
   *   <li>Pve</li>
   *   <li>PvP</li>
   *   <li>PvPLobby</li>
   *   <li>WvW</li>
   * </ul>
   *
   * @return An array of strings indicating the possible game modes.
   */
  @JsonProperty("game_types")
  List<String> getGameTypes();

  /**
   * <p>A list of flags that detail extra properties of the item. Possible
   * values are:</p>
   *
   * <ul>
   *   <li>AccountBound</li>
   *   <li>HideSuffix</li>
   *   <li>NoMysticForge</li>
   *   <li>NoSell</li>
   *   <li>NoSalvage</li>
   *   <li>NotUpgradeable</li>
   *   <li>NoUnderwater</li>
   *   <li>SoulbindOnAcquire</li>
   *   <li>SouldBindOnUse</li>
   *   <li>Unique</li>
   * </ul>
   *
   * @return An array of strings indicating the extra properties, or an empty
   * list for none.
   */
  @JsonProperty("flags")
  List<String> getFlags();

  /**
   * <p>A list of races or classes that are able to use the item. The possible
   * values are:</p>
   *
   * <ul>
   *   <li>Asura</li>
   *   <li>Charr</li>
   *   <li>Human</li>
   *   <li>Norn</li>
   *   <li>Sylvari</li>
   *   <li>Guardian</li>
   *   <li>Warrior</li>
   * </ul>
   *
   * @return A list of allowed races/classes.
   */
  @JsonProperty("restrictions")
  List<String> getRestrictions();

  /*~~~~ Specialized Item Properties ~~~~*/
  ArmorItemProperties getArmor();
  BagItemProperties getBag();
  ConsumableItemProperties getConsumable();
  ContainerItemProperties getContainer();
  GizmoItemProperties getGizmo();
  TrinketItemProperties getTrinket();
  UpgradeComponentItemProperties getUpgradeComponent();
  WeaponItemProperties getWeapon();
}

package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;
import com.jrfom.gw2.jackson.deserializers.ItemDeserializer;

/**
 * See {@link com.jrfom.gw2.api.model.items.Item}.
 */
// TODO: add missing "BackItem" type
@JsonSubTypes({
  @JsonSubTypes.Type(value = ArmorItem.class),
  @JsonSubTypes.Type(value = BagItem.class),
  @JsonSubTypes.Type(value = ConsumableItem.class),
  @JsonSubTypes.Type(value = ContainerItem.class),
  @JsonSubTypes.Type(value = CraftingMaterialItem.class),
  @JsonSubTypes.Type(value = GizmoItem.class),
  @JsonSubTypes.Type(value = MiniPetItem.class),
  @JsonSubTypes.Type(value = TrinketItem.class),
  @JsonSubTypes.Type(value = TrophyItem.class),
  @JsonSubTypes.Type(value = UpgradeComponentItem.class),
  @JsonSubTypes.Type(value = WeaponItem.class)
})
@JsonDeserialize(using = ItemDeserializer.class)
@Gw2ApiVersion("v1")
public class GenericItem implements Item {
  @JsonProperty("item_id")
  private int itemId;
  private String name;
  private String description;
  private String type; // TODO: object-ify this
  private int level;
  private String rarity; // TODO: object-ify this
  @JsonProperty("vendor_value")
  private Coins vendorValue;
  @JsonProperty("icon_file_id")
  private int iconFileId;
  @JsonProperty("icon_file_signature")
  private String iconFileSignature;
  @JsonProperty("game_types")
  private ArrayList<String> gameTypes;
  private ArrayList<String> flags;
  private ArrayList<String> restrictions;

  public GenericItem() {}

  /**
   * {@inheritDoc}
   */
  public Integer getItemId() {
    return this.itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * {@inheritDoc}
   */
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * {@inheritDoc}
   */
  public Integer getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * {@inheritDoc}
   */
  public String getRarity() {
    return this.rarity;
  }

  public void setRarity(String rarity) {
    this.rarity = rarity;
  }

  /**
   * {@inheritDoc}
   */
  public Coins getVendorValue() {
    return this.vendorValue;
  }

  public void setVendorValue(Coins vendorValue) {
    this.vendorValue = vendorValue;
  }

  /**
   * {@inheritDoc}
   */
  public Integer getIconFileId() {
    return this.iconFileId;
  }

  public void setIconFileId(int iconFileId) {
    this.iconFileId = iconFileId;
  }

  /**
   * {@inheritDoc}
   */
  public String getIconFileSignature() {
    return this.iconFileSignature;
  }

  public void setIconFileSignature(String iconFileSignature) {
    this.iconFileSignature = iconFileSignature;
  }

  /**
   * {@inheritDoc}
   */
  public ArrayList<String> getGameTypes() {
    return this.gameTypes;
  }

  public void setGameTypes(ArrayList<String> gameTypes) {
    this.gameTypes = gameTypes;
  }

  /**
   * {@inheritDoc}
   */
  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  /**
   * {@inheritDoc}
   */
  public ArrayList<String> getRestrictions() {
    return this.restrictions;
  }

  public void setRestrictions(ArrayList<String> restrictions) {
    this.restrictions = restrictions;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.ArmorItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ArmorItemProperties}
   * or null.
   */
  @Override
  public ArmorItemProperties getArmor() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.BagItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.BagItemProperties}
   * or null.
   */
  @Override
  public BagItemProperties getBag() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.ConsumableItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ConsumableItemProperties}
   * or null.
   */
  @Override
  public ConsumableItemProperties getConsumable() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.ContainerItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ContainerItemProperties}
   * or null.
   */
  @Override
  public ContainerItemProperties getContainer() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.GizmoItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.GizmoItemProperties}
   * or null.
   */
  @Override
  public GizmoItemProperties getGizmo() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.TrinketItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.TrinketItemProperties}
   * or null.
   */
  @Override
  public TrinketItemProperties getTrinket() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.UpgradeComponentItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.UpgradeComponentItemProperties}
   * or null.
   */
  @Override
  public UpgradeComponentItemProperties getUpgradeComponent() {
    return null;
  }

  /**
   * See {@link com.jrfom.gw2.api.model.items.WeaponItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.WeaponItemProperties}
   * or null.
   */
  @Override
  public WeaponItemProperties getWeapon() {
    return null;
  }
}
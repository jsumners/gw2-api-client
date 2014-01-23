package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;
import com.jrfom.gw2.jackson.deserializers.ItemDeserializer;

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

  public Integer getItemId() {
    return this.itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getRarity() {
    return this.rarity;
  }

  public void setRarity(String rarity) {
    this.rarity = rarity;
  }

  public Coins getVendorValue() {
    return this.vendorValue;
  }

  public void setVendorValue(Coins vendorValue) {
    this.vendorValue = vendorValue;
  }

  public Integer getIconFileId() {
    return this.iconFileId;
  }

  public void setIconFileId(int iconFileId) {
    this.iconFileId = iconFileId;
  }

  public String getIconFileSignature() {
    return this.iconFileSignature;
  }

  public void setIconFileSignature(String iconFileSignature) {
    this.iconFileSignature = iconFileSignature;
  }

  public ArrayList<String> getGameTypes() {
    return this.gameTypes;
  }

  public void setGameTypes(ArrayList<String> gameTypes) {
    this.gameTypes = gameTypes;
  }

  public ArrayList<String> getFlags() {
    return this.flags;
  }

  public void setFlags(ArrayList<String> flags) {
    this.flags = flags;
  }

  public ArrayList<String> getRestrictions() {
    return this.restrictions;
  }

  public void setRestrictions(ArrayList<String> restrictions) {
    this.restrictions = restrictions;
  }
}
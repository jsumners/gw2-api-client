package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Represents and in-game item that is a piece of armor.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "armor"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArmorItem extends GenericItem {
  private ArmorItemProperties armor;

  public ArmorItem() {}

  /**
   * Retrieve the armor specific properties for this item.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ArmorItemProperties}.
   */
  public ArmorItemProperties getArmor() {
    return this.armor;
  }

  public void setArmor(ArmorItemProperties armor) {
    this.armor = armor;
  }
}
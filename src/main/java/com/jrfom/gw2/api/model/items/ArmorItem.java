package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "armor"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArmorItem extends Item {
  private ArmorItemProperties armor;

  public ArmorItem() {}

  public ArmorItemProperties getArmor() {
    return this.armor;
  }

  public void setArmor(ArmorItemProperties armor) {
    this.armor = armor;
  }
}
package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "weapon"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class WeaponItem extends Item {
  private WeaponItemProperties weapon;

  public WeaponItemProperties getWeapon() {
    return this.weapon;
  }

  public void setWeapon(WeaponItemProperties weapon) {
    this.weapon = weapon;
  }
}
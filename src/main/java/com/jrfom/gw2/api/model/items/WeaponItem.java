package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is a weapon.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "weapon"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class WeaponItem extends GenericItem {
  private WeaponItemProperties weapon;

  /**
   * The set of properties that are specific to a
   * {@link com.jrfom.gw2.api.model.items.WeaponItem}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.WeaponItemProperties}.
   */
  public WeaponItemProperties getWeapon() {
    return this.weapon;
  }

  public void setWeapon(WeaponItemProperties weapon) {
    this.weapon = weapon;
  }
}
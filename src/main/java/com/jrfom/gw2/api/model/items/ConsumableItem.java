package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is consumable.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "consumable"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class ConsumableItem extends GenericItem {
  private ConsumableItemProperties consumable;

  public ConsumableItem() {}

  /**
   * Retrieve the consumable specific properties for this item.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ConsumableItemProperties}.
   */
  public ConsumableItemProperties getConsumable() {
    return this.consumable;
  }

  public void setConsumable(ConsumableItemProperties consumable) {
    this.consumable = consumable;
  }
}
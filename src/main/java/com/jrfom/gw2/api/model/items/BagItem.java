package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is a bag.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "bag"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class BagItem extends GenericItem {
  private BagItemProperties bag;

  public BagItem() {}

  /**
   * Retrieve the bag specific properties for this item.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.BagItemProperties}.
   */
  public BagItemProperties getBag() {
    return this.bag;
  }

  public void setBag(BagItemProperties bag) {
    this.bag = bag;
  }
}
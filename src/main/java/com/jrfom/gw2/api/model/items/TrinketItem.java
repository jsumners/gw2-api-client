package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "trinket"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class TrinketItem extends Item {
  private TrinketItemProperties trinket;

  public TrinketItem() {}

  public TrinketItemProperties getTrinket() {
    return this.trinket;
  }

  public void setTrinket(TrinketItemProperties trinket) {
    this.trinket = trinket;
  }
}
package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is an upgrade component.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "upgrade_component"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v2")
public class UpgradeComponentItem extends GenericItem {
  @JsonProperty("upgrade_component")
  private UpgradeComponentItemProperties upgradeComponent;

  public UpgradeComponentItem() {}

  /**
   * Retrieve the set of properties that are specific to an
   * {@link com.jrfom.gw2.api.model.items.UpgradeComponentItem}.
   * @return
   */
  public UpgradeComponentItemProperties getUpgradeComponent() {
    return this.upgradeComponent;
  }

  public void setUpgradeComponent(UpgradeComponentItemProperties upgradeComponent) {
    this.upgradeComponent = upgradeComponent;
  }
}
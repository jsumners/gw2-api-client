package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is a container.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "container"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class ContainerItem extends GenericItem {
  private ContainerItemProperties container;

  public ContainerItem() {}

  /**
   * Retreive the container specific properties for this item.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ConsumableItemProperties}.
   */
  public ContainerItemProperties getContainer() {
    return this.container;
  }

  public void setContainer(ContainerItemProperties container) {
    this.container = container;
  }
}
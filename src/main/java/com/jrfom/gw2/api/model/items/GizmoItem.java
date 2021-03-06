package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents and in-game item that is a gizmo.
 */
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "gizmo"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class GizmoItem extends GenericItem {
  private GizmoItemProperties gizmo;

  public GizmoItem() {}

  /**
   * Retrieve the set of properties that is specific to
   * {@link com.jrfom.gw2.api.model.items.GizmoItem}.
   * @return
   */
  public GizmoItemProperties getGizmo() {
    return this.gizmo;
  }

  public void setGizmo(GizmoItemProperties gizmo) {
    this.gizmo = gizmo;
  }
}
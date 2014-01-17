package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NONE,
  include = JsonTypeInfo.As.WRAPPER_OBJECT,
  property = "gizmo"
)
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class GizmoItem extends Item {
  private GizmoItemProperties gizmo;

  public GizmoItem() {}

  public GizmoItemProperties getGizmo() {
    return this.gizmo;
  }

  public void setGizmo(GizmoItemProperties gizmo) {
    this.gizmo = gizmo;
  }
}
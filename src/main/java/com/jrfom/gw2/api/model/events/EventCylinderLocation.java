package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class EventCylinderLocation extends EventSphereLocation {
  private double height;

  public EventCylinderLocation() {
    this.setType("cylinder");
  }

  public Double getHeight() {
    return this.height;
  }

  public void setHeight(double height) {
    this.height = height;
  }
}
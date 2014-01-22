package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an {@link com.jrfom.gw2.api.model.events.Event}
 * {@link com.jrfom.gw2.api.model.events.Location} that is described by
 * a sphere.
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class EventCylinderLocation extends EventSphereLocation {
  private double height;

  public EventCylinderLocation() {
    this.setType("cylinder");
  }

  /**
   * {@inheritDoc}
   */
  public Double getHeight() {
    return this.height;
  }

  public void setHeight(double height) {
    this.height = height;
  }
}
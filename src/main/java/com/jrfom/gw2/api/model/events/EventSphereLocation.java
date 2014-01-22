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
public class EventSphereLocation extends EventLocation {
  private double radius;
  private double rotation;

  public EventSphereLocation() {
    this.setType("sphere");
  }

  /**
   * {@inheritDoc}
   */
  public Double getRadius() {
    return this.radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  /**
   * The degree(?) rotation of the sphere.
   *
   * @return The rotation as returned by the remote API.
   */
  public Double getRotation() {
    return this.rotation;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }
}
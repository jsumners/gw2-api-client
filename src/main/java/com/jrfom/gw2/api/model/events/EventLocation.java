package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * A generic implementation of {@link com.jrfom.gw2.api.model.events.EventLocation}
 * to be used during deserialization and serialization. This has no purpose
 * outside of the API modeling.
 */
@Gw2ApiVersion("v2")
public class EventLocation implements Location {
  private String type;
  private EventCenterCoordinate center;

  public EventLocation() {}

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EventCenterCoordinate getCenter() {
    return this.center;
  }

  public void setCenter(EventCenterCoordinate center) {
    this.center = center;
  }

  @Override
  public Double getHeight() {
    return null;
  }

  @Override
  public Double getRadius() {
    return null;
  }

  @Override
  public Double getRotation() {
    return null;
  }

  @Override
  public Range getzRange() {
    return null;
  }

  @Override
  public ArrayList<Point> getPoints() {
    return null;
  }
}
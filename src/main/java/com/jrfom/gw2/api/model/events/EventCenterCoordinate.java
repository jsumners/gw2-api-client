package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.EventCenterCoordinateDeserializer;
import com.jrfom.gw2.jackson.serializers.EventCenterCoordinateSerializer;

@JsonDeserialize(using = EventCenterCoordinateDeserializer.class)
@JsonSerialize(using = EventCenterCoordinateSerializer.class)
@Gw2ApiVersion("v1")
public class EventCenterCoordinate {
  private double x;
  private double y;
  private double z;

  public EventCenterCoordinate() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  public double getX() {
    return this.x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return this.y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return this.z;
  }

  public void setZ(double z) {
    this.z = z;
  }
}
